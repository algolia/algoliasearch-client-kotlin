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

    @Test
    fun hosts() {
        assertEquals("https://${applicationId.string}-dsn.algolia.net", retryLogic.hosts.first())
        assertTrue {
            retryLogic.hosts.contains("https://${applicationId.string}-1.algolianet.com")
            retryLogic.hosts.contains("https://${applicationId.string}-2.algolianet.com")
            retryLogic.hosts.contains("https://${applicationId.string}-3.algolianet.com")
        }
        assertEquals(4, retryLogic.hosts.size)
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

            retryLogic.retry(1000L, route) {
                retry++
                if (retry == 0) delay(2000L)
                httpClient.get<HttpResponse>()
            }
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
                val index = retry % count
                assertEquals(retryLogic.hosts[index] + route, path)
                if (retry < count) delay(150L * (retry + 1))
                httpClient.get<HttpResponse>(path)
                assertEquals(Status.Down, retryLogic.statuses[index].first)
            }
            assertEquals(Status.Up, retryLogic.statuses.first().first)
            assertEquals(count, retry)
        }
    }
}