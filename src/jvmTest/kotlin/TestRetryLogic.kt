import client.ApplicationId
import client.host.RetryLogic
import client.host.Status
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockHttpResponse
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertTrue


@RunWith(JUnit4::class)
class TestRetryLogic {

    private val applicationId = ApplicationId("appId")
    private val retryLogic = RetryLogic(applicationId, RetryLogic.Type.Read)
    private val route = "/route"
    private val mockEngine = MockEngine {
        MockHttpResponse(
            call,
            HttpStatusCode.OK,
            ByteReadChannel(""),
            headersOf()
        )
    }
    private val httpClient = HttpClient(mockEngine)
    private val statuses = retryLogic.statuses
    private val hosts = retryLogic.hosts

    @Test
    fun hosts() {
        assertEquals("https://${applicationId.string}-dsn.algolia.net", hosts.first())
        assertTrue {
            hosts.contains("https://${applicationId.string}-1.algolianet.com")
            hosts.contains("https://${applicationId.string}-2.algolianet.com")
            hosts.contains("https://${applicationId.string}-3.algolianet.com")
        }
        assertEquals(4, hosts.size)
    }

    @Test
    fun noRetry() {
        runBlocking {
            var retry = -1

            retryLogic.retry(1000L, route) {
                retry++
                httpClient.get<HttpResponse>()
            }
            assertEquals(0, retry)
        }
    }

    @Test
    fun retryOnce() {
        runBlocking {
            var retry = -1

            retryLogic.retry(1000L, route) { path ->
                retry++
                assertEquals(hosts[retry] + route, path)
                if (retry == 0) delay(2000L)
                httpClient.get<HttpResponse>()
            }
            assertEquals(Status.Down, statuses[0].first)
            assertEquals(Status.Up, statuses[1].first)
            assertEquals(Status.Unknown, statuses[2].first)
            assertEquals(Status.Unknown, statuses[3].first)
            assertEquals(1, retry)
        }
    }

    @Test
    fun retryFour() {
        runBlocking {
            val count = 4
            var retry = -1

            retryLogic.retry(100L, route) { path ->
                retry++
                assertEquals(hosts[retry % count] + route, path)
                if (retry < count) delay(150L * (retry + 1))
                httpClient.get<HttpResponse>(path)
            }
            assertEquals(Status.Up, statuses[0].first)
            assertEquals(Status.Down, statuses[1].first)
            assertEquals(Status.Down, statuses[2].first)
            assertEquals(Status.Down, statuses[3].first)
            assertEquals(count, retry)
        }
    }
}