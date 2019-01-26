package host

import com.algolia.search.model.ApplicationID
import com.algolia.search.host.HostStatus
import com.algolia.search.host.RetryLogic
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockHttpResponse
import io.ktor.client.features.BadResponseStatusException
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
internal class TestRetryLogic {

    private val applicationId = ApplicationID("appId")
    private val retryLogic = RetryLogic(applicationId, RetryLogic.Type.Read)
    private val route = "/route"
    private val client200 = HttpClient(MockEngine { MockHttpResponse(call, HttpStatusCode.OK) })
    private val client404 = HttpClient(MockEngine { MockHttpResponse(call, HttpStatusCode.NotFound) })
    private val statuses = retryLogic.statuses
    private val hosts = retryLogic.hosts

    @Test
    fun hosts() {
        hosts.first() shouldEqual "https://$applicationId-dsn.algolia.net"
        hosts.contains("https://$applicationId-1.algolianet.com").shouldBeTrue()
        hosts.contains("https://$applicationId-2.algolianet.com").shouldBeTrue()
        hosts.contains("https://$applicationId-3.algolianet.com").shouldBeTrue()
        hosts.size shouldEqual 4
    }

    @Test
    fun noRetry() {
        runBlocking {
            var retry = -1

            retryLogic.retry(1000L, route) {
                retry++
                client200.get<HttpResponse>()
            }
            retry shouldEqual 0
        }
        statuses[0].first shouldEqual HostStatus.Up
        statuses[1].first shouldEqual HostStatus.Unknown
        statuses[2].first shouldEqual HostStatus.Unknown
        statuses[3].first shouldEqual HostStatus.Unknown
    }

    @Test
    fun retryOnce() {
        runBlocking {
            var retry = -1

            retryLogic.retry(1000L, route) { path ->
                retry++
                path shouldEqual hosts[retry] + route
                if (retry == 0) delay(2000L)
                client200.get<HttpResponse>()
            }
            statuses[0].first shouldEqual HostStatus.Down
            statuses[1].first shouldEqual HostStatus.Up
            statuses[2].first shouldEqual HostStatus.Unknown
            statuses[3].first shouldEqual HostStatus.Unknown
            retry shouldEqual 1
        }
    }

    @Test
    fun retryFour() {
        runBlocking {
            val count = 4
            var retry = -1

            retryLogic.retry(100L, route) { path ->
                retry++
                path shouldEqual hosts[retry % count] + route
                if (retry < count) delay(150L * (retry + 1))
                client200.get<HttpResponse>(path)
            }
            statuses[0].first shouldEqual HostStatus.Up
            statuses[1].first shouldEqual HostStatus.Down
            statuses[2].first shouldEqual HostStatus.Down
            statuses[3].first shouldEqual HostStatus.Down
            retry shouldEqual count
        }
    }

    @Test
    fun test404() {
        runBlocking {
            var retry = -1
            var exceptionIsThrown = false

            try {
                retryLogic.retry(1000L, route) { path ->
                    retry++
                    client404.get<String>(path)
                }
            } catch (exception: BadResponseStatusException) {
                exceptionIsThrown = true
            }
            exceptionIsThrown.shouldBeTrue()
            retry shouldEqual 0
            statuses[0].first shouldEqual HostStatus.Unknown
            statuses[1].first shouldEqual HostStatus.Unknown
            statuses[2].first shouldEqual HostStatus.Unknown
            statuses[3].first shouldEqual HostStatus.Unknown
        }
    }
}