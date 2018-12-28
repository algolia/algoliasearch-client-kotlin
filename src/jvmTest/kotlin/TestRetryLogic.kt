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
import kotlin.test.assertEquals
import kotlin.test.assertTrue


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
                client200.get<HttpResponse>()
            }
            assertEquals(0, retry)
        }
        assertEquals(Status.Up, statuses[0].first)
        assertEquals(Status.Unknown, statuses[1].first)
        assertEquals(Status.Unknown, statuses[2].first)
        assertEquals(Status.Unknown, statuses[3].first)
    }

    @Test
    fun retryOnce() {
        runBlocking {
            var retry = -1

            retryLogic.retry(1000L, route) { path ->
                retry++
                assertEquals(hosts[retry] + route, path)
                if (retry == 0) delay(2000L)
                client200.get<HttpResponse>()
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
                client200.get<HttpResponse>(path)
            }
            assertEquals(Status.Up, statuses[0].first)
            assertEquals(Status.Down, statuses[1].first)
            assertEquals(Status.Down, statuses[2].first)
            assertEquals(Status.Down, statuses[3].first)
            assertEquals(count, retry)
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
            assertTrue(exceptionIsThrown)
            assertEquals(0, retry)
            assertEquals(Status.Unknown, statuses[0].first)
            assertEquals(Status.Unknown, statuses[1].first)
            assertEquals(Status.Unknown, statuses[2].first)
            assertEquals(Status.Unknown, statuses[3].first)
        }
    }
}