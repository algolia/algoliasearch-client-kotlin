
import client.data.ApplicationId
import client.host.RetryLogic
import client.host.Status
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


@RunWith(JUnit4::class)
class TestRetryLogic {

    private val applicationId = ApplicationId("appId")
    private val retryLogic = RetryLogic(applicationId, RetryLogic.Type.Read)
    private val route = "/route"
    private val client200 = HttpClient(MockEngine { MockHttpResponse(call, HttpStatusCode.OK) })
    private val client404 = HttpClient(MockEngine { MockHttpResponse(call, HttpStatusCode.NotFound) })
    private val statuses = retryLogic.statuses
    private val hosts = retryLogic.hosts

    @Test
    fun hosts() {
        "https://$applicationId-dsn.algolia.net" shouldEqual hosts.first()
        hosts.contains("https://$applicationId-1.algolianet.com").shouldBeTrue()
        hosts.contains("https://$applicationId-2.algolianet.com").shouldBeTrue()
        hosts.contains("https://$applicationId-3.algolianet.com").shouldBeTrue()
        4 shouldEqual hosts.size
    }

    @Test
    fun noRetry() {
        runBlocking {
            var retry = -1

            retryLogic.retry(1000L, route) {
                retry++
                client200.get<HttpResponse>()
            }
            0 shouldEqual retry
        }
        Status.Up shouldEqual statuses[0].first
        Status.Unknown shouldEqual statuses[1].first
        Status.Unknown shouldEqual statuses[2].first
        Status.Unknown shouldEqual statuses[3].first
    }

    @Test
    fun retryOnce() {
        runBlocking {
            var retry = -1

            retryLogic.retry(1000L, route) { path ->
                retry++
                hosts[retry] + route shouldEqual path
                if (retry == 0) delay(2000L)
                client200.get<HttpResponse>()
            }
            Status.Down shouldEqual statuses[0].first
            Status.Up shouldEqual statuses[1].first
            Status.Unknown shouldEqual statuses[2].first
            Status.Unknown shouldEqual statuses[3].first
            1 shouldEqual retry
        }
    }

    @Test
    fun retryFour() {
        runBlocking {
            val count = 4
            var retry = -1

            retryLogic.retry(100L, route) { path ->
                retry++
                hosts[retry % count] + route shouldEqual path
                if (retry < count) delay(150L * (retry + 1))
                client200.get<HttpResponse>(path)
            }
            Status.Up shouldEqual statuses[0].first
            Status.Down shouldEqual statuses[1].first
            Status.Down shouldEqual statuses[2].first
            Status.Down shouldEqual statuses[3].first
            count shouldEqual retry
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
            0 shouldEqual retry
            Status.Unknown shouldEqual statuses[0].first
            Status.Unknown shouldEqual statuses[1].first
            Status.Unknown shouldEqual statuses[2].first
            Status.Unknown shouldEqual statuses[3].first
        }
    }
}