import client.Client
import client.data.ApiKey
import client.data.ApplicationId
import client.data.Index
import client.data.IndexQuery
import client.data.Query
import client.data.Settings
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TestEndpoints {

    private val apiKey = ApiKey("dc8e9efcfe38f7fbfb996047af06d8c5")
    private val applicationId = ApplicationId("latency")
    private val api = Client(applicationId, apiKey)
    private val index = Index("products_android_demo")

    @Test
    fun listIndexes() {
        runBlocking {
            api.getListIndexes()
        }
    }

    @Test
    fun search() {
        runBlocking {
            api.search(index)
        }
    }

    @Test
    fun browse() {
        runBlocking {
            val responseA = api.browse(index)

            responseA.cursor?.let { api.browse(index, it) }
        }
    }

    @Test
    fun multiQueries() {
        runBlocking {
            val queries = listOf(
                IndexQuery(index, Query("a")),
                IndexQuery(index, Query("b"))
            )
            api.multipleQueries(queries)
        }
    }

    @Test
    fun searchForFacetValue() {
        runBlocking {
            val maxFacetHits = 2
            val response = api.searchForFacetValue(
                index,
                "color",
                maxFacetHits = maxFacetHits,
                facetQuery = "co",
                query = Query(maxFacetHits = maxFacetHits)
            )

            (response.facetHits.size <= maxFacetHits).shouldBeTrue()
        }
    }

    @Test
    fun getSettings() {
        runBlocking {
            val settings = api.getSettings(index)

            println(settings)
        }
    }

    @Test
    fun getTask() {
        runBlocking {
            val task = api.setSettings(index, Settings())

            println(api.getTask(index, task.taskID))
        }
    }

    @Test
    fun setSettings() {
        runBlocking {
            val task = api.setSettings(index, Settings())

            println(task)
        }
    }
}