package suite

import clientAdmin1
import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.serialize.Json
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.logging.LogLevel
import io.ktor.http.ContentType
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.delay
import runBlocking
import shouldEqual
import kotlin.test.Test

internal class TestSuiteDNS {

    private val readTimeout = 500L
    private val shouldTimeout = 600L
    private val shouldNotTimeout = 400L
    private var requestCount = 0
    private val mockEngine = MockEngine {
        val delay = if (requestCount == 0) shouldTimeout else shouldNotTimeout

        requestCount++
        delay(delay)
        respond(
            headers = headersOf(
                "Content-Type",
                listOf(ContentType.Application.Json.toString())
            ),
            content = ByteReadChannel(Json.stringify(ResponseSearch.serializer(), ResponseSearch()))
        )
    }

    private val client = ClientSearch(
        ConfigurationSearch(
            clientAdmin1.applicationID,
            clientAdmin1.apiKey,
            readTimeout = readTimeout,
            engine = mockEngine,
            logLevel = LogLevel.INFO
        )
    )

    @Test
    fun test() {
        runBlocking {
            val index = client.initIndex(IndexName("test"))

            index.search()
            client.transport.hosts.first().retryCount shouldEqual 1
        }
    }
}
