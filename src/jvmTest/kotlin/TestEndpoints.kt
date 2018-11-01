import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import client.*
import io.ktor.client.features.BadResponseStatus
import kotlinx.coroutines.runBlocking
import kotlin.test.fail
import client.query.SearchParameters
import kotlinx.serialization.json.JSON


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
    fun params() {
        val parameters = SearchParameters()
        println(JSON.stringify(parameters))
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
                    numericFilters = listOf(listOf("nbLike > 0", "nbLike < 1000"), listOf("nbLike != 381"))
                )
                val response = client.searchQuery(index, searchParameters)
                println(response)
            } catch (exception: BadResponseStatus) {
                fail(exception.localizedMessage)
            }
        }
    }
}