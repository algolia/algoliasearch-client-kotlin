package configuration

import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.AlgoliaSearchClient
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.clientUserAgent
import com.algolia.search.dsl.requestOptions
import com.algolia.search.internal.BuildConfig
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.client.engine.mock.respondOk
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.UserAgent
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import kotlin.test.Test
import runBlocking
import shouldBeTrue
import shouldEqual
import shouldFailWith

internal class TestUserAgent {

    private val applicationID = ApplicationID("applicationID")
    private val apiKey = APIKey("apiKey")
    private val userAgentKey = "User-Agent"

    @Test
    fun testUserAgent() {
        val version = AlgoliaSearchClient.version

        clientUserAgent(version) shouldEqual "Algolia for Kotlin ($version)"
    }

    @Test
    fun overridingUserAgentInConfigurationShouldBeIgnored() {
        runBlocking {
            val configuration = ConfigurationSearch(
                applicationID = applicationID,
                engine = MockEngine { respondOk() },
                apiKey = apiKey,
                httpClientConfig = {
                    install(UserAgent) {
                        agent = "Test"
                    }
                }
            )
            val client = ClientSearch(configuration)
            val request = client.httpClient.request(HttpRequestBuilder())
            val headers = request.call.request.headers

            headers.contains(userAgentKey).shouldBeTrue()
            headers[userAgentKey] shouldEqual clientUserAgent(BuildConfig.version)
        }
    }

    @Test
    fun overridingUserAgentInRequestOptionsShouldNotBeIgnored() {
        runBlocking {
            val expected = "My User Agent"
            val configuration = ConfigurationSearch(
                applicationID = applicationID,
                engine = MockEngine {
                    respondBadRequest()
                },
                apiKey = apiKey
            )
            val client = ClientSearch(configuration)
            val requestOptions = requestOptions { header(userAgentKey, expected) }
            val request = shouldFailWith<ResponseException> {
                client.listIndices(requestOptions)
            }
            val headers = request.response.call.request.headers

            headers.get(userAgentKey) shouldEqual expected
        }
    }
}
