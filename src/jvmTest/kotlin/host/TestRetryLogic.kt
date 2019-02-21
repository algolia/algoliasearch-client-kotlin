package host

import com.algolia.search.exception.RetryableException
import com.algolia.search.host.HostState
import com.algolia.search.host.RetryLogic
import com.algolia.search.host.readHosts
import com.algolia.search.model.ApplicationID
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockHttpResponse
import io.ktor.client.features.BadResponseStatusException
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual
import shouldNotBeNull


@RunWith(JUnit4::class)
internal class TestRetryLogic {

    private val applicationId = ApplicationID("appId")
    private val retryLogic = RetryLogic(5, applicationId.readHosts())
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
        statuses[0].state shouldEqual HostState.Up
        statuses[1].state shouldEqual HostState.Unknown
        statuses[2].state shouldEqual HostState.Unknown
        statuses[3].state shouldEqual HostState.Unknown
    }

    @Test
    fun retryOnce() {
        runBlocking {
            var retry = -1

            retryLogic.retry(1000L, route) { url ->
                retry++
                url shouldEqual hosts[retry] + route
                if (retry == 0) delay(2000L)
                client200.get<HttpResponse>()
            }
            statuses[0].state shouldEqual HostState.Down
            statuses[1].state shouldEqual HostState.Up
            statuses[2].state shouldEqual HostState.Unknown
            statuses[3].state shouldEqual HostState.Unknown
            retry shouldEqual 1
        }
    }

    @Test
    fun retryFour() {
        runBlocking {
            val count = 4
            var retry = -1

            retryLogic.retry(100L, route) { url ->
                retry++
                url shouldEqual hosts[retry % count] + route
                if (retry < count) delay(150L * (retry + 1))
                client200.get<HttpResponse>(url)
            }
            statuses[0].state shouldEqual HostState.Up
            statuses[1].state shouldEqual HostState.Down
            statuses[2].state shouldEqual HostState.Down
            statuses[3].state shouldEqual HostState.Down
            retry shouldEqual count
        }
    }

    @Test
    fun test404() {
        runBlocking {
            var retry = -1
            var exceptionIsThrown = false

            try {
                retryLogic.retry(1000L, route) { url ->
                    retry++
                    client404.get<String>(url)
                }
            } catch (exception: BadResponseStatusException) {
                exceptionIsThrown = true
            }
            exceptionIsThrown.shouldBeTrue()
            retry shouldEqual 0
            statuses[0].state shouldEqual HostState.Unknown
            statuses[1].state shouldEqual HostState.Unknown
            statuses[2].state shouldEqual HostState.Unknown
            statuses[3].state shouldEqual HostState.Unknown
        }
    }

    @Test
    fun retryMaxAttempt() {
        var retry = -1
        var thrown: RetryableException? = null

        runBlocking {
            try {
                retryLogic.retry(100L, route) { url ->
                    retry++
                    delay(150L * (retry + 1))
                    client200.get<HttpResponse>(url)
                }
            } catch (exception: RetryableException) {
                thrown = exception
            }
            thrown.shouldNotBeNull()
            thrown!!.let {
                it.exceptions.size shouldEqual it.attempts
                it.attempts shouldEqual 5
                it.exceptions.forEach { exception ->
                    (exception is TimeoutCancellationException).shouldBeTrue()
                }
            }
        }
    }
}