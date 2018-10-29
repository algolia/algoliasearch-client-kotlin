import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import client.*
import io.ktor.client.features.BadResponseStatus
import kotlinx.coroutines.runBlocking
import kotlin.test.fail


@RunWith(JUnit4::class)
class TestEndpoints {

    val apiKey = ApiKey("dc8e9efcfe38f7fbfb996047af06d8c5")
    val applicationId = ApplicationId("latency")
    val client = Client(applicationId, apiKey)
    val index = Index("products_android_demo")

    @Test
    fun listIndexes() {
        runBlocking {
            println(client.getListIndexes())
        }
    }

    @Test
    fun search() {
        runBlocking {
            println(client.search(index))
        }
    }

    @Test
    fun searchQuery() {
        runBlocking {
            try {
                val searchParameters = SearchParameters(
                    restrictSearchableAttributes = listOf("color")
                )
                val response = client.searchQuery(index, searchParameters)
                println(response)
            } catch (exception: BadResponseStatus) {
                fail(exception.localizedMessage)
            }
        }
    }
}