package suite

import clientAdmin1
import com.algolia.search.client.ClientSearch
import com.algolia.search.client.internal.ClientSearchImpl
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.logging.LogLevel
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.serialize.internal.Json
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.SocketTimeoutException
import io.ktor.http.ContentType
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import shouldEqual
import kotlin.test.Test

internal class TestSuiteDNS {

    private val readTimeout = 500L
    private val shouldTimeout = 600L
    private val shouldNotTimeout = 400L
    private var requestCount = 0
    private val mockEngine = MockEngine { call ->
        val delay = if (requestCount == 0) shouldTimeout else shouldNotTimeout
        requestCount++
        val timeout = requireNotNull(call.getCapabilityOrNull(HttpTimeout)) { "Timeout feature required" }
        val socketTimeoutMillis = requireNotNull(timeout.socketTimeoutMillis) { "socket timeout required" }
        if (delay > socketTimeoutMillis) throw SocketTimeoutException(call, null)

        respond(
            headers = headersOf(
                "Content-Type",
                listOf(ContentType.Application.Json.toString())
            ),
            content = ByteReadChannel(Json.encodeToString(ResponseSearch.serializer(), ResponseSearch()))
        )
    }

    private val client = ClientSearch(
        ConfigurationSearch(
            clientAdmin1.applicationID,
            clientAdmin1.apiKey,
            readTimeout = readTimeout,
            engine = mockEngine,
            logLevel = LogLevel.Info
        )
    )

    @Test
    fun test() {
        runTest {
            val index = client.initIndex(IndexName("test"))

            index.search()
            (client as ClientSearchImpl).transport.hosts.first().retryCount shouldEqual 1
        }
    }
}
