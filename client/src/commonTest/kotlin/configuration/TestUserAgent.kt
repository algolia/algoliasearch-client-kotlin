package configuration

import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.AlgoliaSearchClient
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.clientUserAgent
import com.algolia.search.configuration.internal.extension.AlgoliaAgent
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.serialize.internal.Key
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respondOk
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import kotlinx.coroutines.test.runTest
import shouldBeTrue
import shouldEqual
import kotlin.test.Test

internal class TestUserAgent {

    private val applicationID = ApplicationID("applicationID")
    private val apiKey = APIKey("apiKey")

    @Test
    fun testUserAgent() {
        val version = AlgoliaSearchClient.version

        clientUserAgent(version) shouldEqual "Algolia for Kotlin ($version)"
    }

    @Test
    fun overridingUserAgentInConfigurationShouldBeIgnored() {
        runTest {
            val testAgent = "Test"
            val configuration = ConfigurationSearch(
                applicationID = applicationID,
                engine = MockEngine { respondOk() },
                apiKey = apiKey,
                httpClientConfig = {
                    install(AlgoliaAgent) {
                        agent = testAgent
                    }
                }
            )
            val client = ClientSearch(configuration)
            val request = client.httpClient.request(HttpRequestBuilder())
            val urlParameters = request.call.request.url.parameters

            urlParameters.contains(Key.AlgoliaAgent).shouldBeTrue()
            urlParameters[Key.AlgoliaAgent] shouldEqual testAgent
        }
    }
}
