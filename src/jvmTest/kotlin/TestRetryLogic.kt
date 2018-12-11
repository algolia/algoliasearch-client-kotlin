import client.ApplicationId
import client.host.Hosts
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockHttpResponse
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestRetryLogic {

    private val hosts = Hosts(ApplicationId("appId"))
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
    fun noRetry() {
        runBlocking {
            var retry = -1

            hosts.retryLogic(1000L, route) {
                retry++
                httpClient.get()
            }
            assertEquals(0, retry)
        }
    }

    @Test
    fun retryOnce() {
        runBlocking {
            var retry = -1

            hosts.retryLogic(1000L, route) {
                retry++
                if (retry == 0) delay(2000L)
                httpClient.get()
            }
            assertEquals(1, retry)
        }
    }

    @Test
    fun retryThree() {
        runBlocking {
            var retry = -1

            hosts.retryLogic(500L, route) { path ->
                retry++
                when (retry) {
                    0 -> assertEquals(hosts.fallbackHosts[0] + route, path)
                    1 -> assertEquals(hosts.fallbackHosts[1] + route, path)
                    2 -> assertEquals(hosts.fallbackHosts[2] + route, path)
                }
                if (retry < 3) delay(500L * (retry + 1))
                httpClient.get(path)
            }
            assertEquals(3, retry)
        }
    }
}