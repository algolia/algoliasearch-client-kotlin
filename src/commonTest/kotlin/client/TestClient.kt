package client

import com.algolia.search.client.ClientAnalytics
import com.algolia.search.client.ClientInsights
import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.*
import com.algolia.search.helper.toAPIKey
import com.algolia.search.helper.toApplicationID
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockHttpResponse
import io.ktor.http.HttpStatusCode
import shouldEqual
import kotlin.test.Test


internal class TestClient {

    private val apiKey = "apiKey".toAPIKey()
    private val appID = "appID".toApplicationID()
    private val hosts = listOf(RetryableHost("host"))

    private val engine = MockEngine { MockHttpResponse(it.call, HttpStatusCode.OK) }
    private val configurationSearch = ConfigurationSearch(appID, apiKey, engine = engine, hosts = hosts)
    private val configurationInsights = ConfigurationInsights(appID, apiKey, engine = engine, hosts = hosts)
    private val configurationAnalytics = ConfigurationAnalytics(appID, apiKey, engine = engine, hosts = hosts)

    @Test
    fun search() {
        ClientSearch(configurationSearch) shouldEqual configurationSearch
    }

    @Test
    fun insights() {
        ClientInsights(configurationInsights) shouldEqual configurationInsights
        ClientInsights(configurationInsights) shouldEqual configurationInsights
    }

    @Test
    fun analytics() {
        ClientAnalytics(configurationAnalytics) shouldEqual configurationAnalytics
        ClientAnalytics(configurationAnalytics) shouldEqual configurationAnalytics
    }

    private infix fun Configuration.shouldEqual(expected: Configuration) {
        expected.let {
            apiKey shouldEqual it.apiKey
            applicationID shouldEqual it.applicationID
            hosts shouldEqual it.hosts
            engine shouldEqual it.engine
            logLevel shouldEqual it.logLevel
            readTimeout shouldEqual it.readTimeout
            writeTimeout shouldEqual it.writeTimeout
        }
    }
}