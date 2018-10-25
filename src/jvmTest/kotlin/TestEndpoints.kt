import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import client.*
import kotlinx.coroutines.runBlocking


@RunWith(JUnit4::class)
class TestEndpoints {

    val apiKey = ApiKey("dc8e9efcfe38f7fbfb996047af06d8c5")
    val applicationId = ApplicationId("latency")
    val client = Client(applicationId, apiKey)
    val index = Index("products")

    @Test
    fun listIndexes() {
        runBlocking {
            client.getListIndexes().items.isNotEmpty()
        }
    }

    @Test
    fun search() {
        runBlocking {
            client.search(index).hits
        }
    }
}