
import client.ApiKey
import client.ApplicationId
import client.Client
import client.Index
import client.query.IndexQuery
import client.query.Query
import io.ktor.client.features.BadResponseStatusException
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertTrue
import kotlin.test.fail


@RunWith(JUnit4::class)
class TestEndpoints {

    private val apiKey = ApiKey("dc8e9efcfe38f7fbfb996047af06d8c5")
    private val applicationId = ApplicationId("latency")
    private val api = Client(applicationId, apiKey)
    private val index = Index("products_android_demo")

    @Test
    fun listIndexes() {
        runBlocking {
            println(api.getListIndexes())
        }
    }

    @Test
    fun search() {
        runBlocking {
            try {
                val response = api.search(index)

                println(response)
            } catch (exception: BadResponseStatusException) {
                fail(exception.localizedMessage)
            }
        }
    }

    @Test
    fun browse() {
        runBlocking {
            try {
                val responseA = api.browse(index)

                println(responseA)
                responseA.cursor?.let {
                    val responseB = api.browse(index, it)

                    println(responseB)
                }
            } catch (exception: BadResponseStatusException) {
                fail(exception.localizedMessage)
            }
        }
    }

    @Test
    fun multiQueries() {
        runBlocking {
            try {
                val queries = listOf(
                    IndexQuery(index, Query("a")),
                    IndexQuery(index, Query("b"))
                )
                val response = api.multipleQueries(queries)

                println(response)
            } catch (exception: BadResponseStatusException) {
                fail(exception.localizedMessage)
            }
        }
    }

    @Test
    fun searchForFacetValue() {
        runBlocking {
            try {
                val maxFacetHits = 2
                val response = api.searchForFacetValue(
                    index,
                    "color",
                    maxFacetHits = maxFacetHits,
                    facetQuery = "co",
                    query = Query(maxFacetHits = maxFacetHits)
                )

                println(response)
                assertTrue(response.facetHits.size <= maxFacetHits)
            } catch (exception: BadResponseStatusException) {
                exception.printStackTrace()
                fail(exception.localizedMessage)
            }
        }
    }

    @Test
    fun timeout() {
        val client = Client(applicationId, apiKey)

        runBlocking {
            client.getListIndexes()
        }
    }
}