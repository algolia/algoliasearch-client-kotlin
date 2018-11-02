import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import client.*
import io.ktor.client.features.BadResponseStatus
import kotlinx.coroutines.runBlocking
import kotlin.test.fail
import client.query.QuerySerializable
import kotlinx.serialization.json.JSON
import client.query.*


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
        val parameters = QuerySerializable()
        println(JSON.stringify(parameters))
    }

    @Test
    fun search() {
        runBlocking {
            try {
                val response = client.search(index, Query())
                println(response)
            } catch (exception: BadResponseStatus) {
                fail(exception.localizedMessage)
            }
        }
    }

    @Test
    fun browse() {
        runBlocking {
            try {
                val responseA = client.browse(index, Query())

                println(responseA)
                responseA.cursor?.let {
                    val responseB = client.browse(index, it)

                    println(responseB)
                }
            } catch (exception: BadResponseStatus) {
                fail(exception.localizedMessage)
            }
        }
    }
}