package configuration

import BuildConfig
import com.algolia.search.configuration.AlgoliaSearchClient
import com.algolia.search.configuration.ConfigurationAnalytics
import com.algolia.search.configuration.ConfigurationInsights
import com.algolia.search.configuration.ConfigurationPlaces
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.defaultReadTimeout
import com.algolia.search.configuration.defaultWriteTimeout
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.analyticsHosts
import com.algolia.search.transport.insightHosts
import com.algolia.search.transport.placesHosts
import com.algolia.search.transport.searchHosts
import io.ktor.client.features.logging.LogLevel
import shouldBeNull
import shouldEqual
import kotlin.test.Test

internal class TestConfiguration {

    private val applicationID = ApplicationID("applicationID")
    private val apiKey = APIKey("apiKey")

    @Test
    fun testAlgoliaSearchClient() {
        AlgoliaSearchClient.version shouldEqual BuildConfig.version
    }

    @Test
    fun configurationSearch() {
        ConfigurationSearch(applicationID, apiKey).apply {
            writeTimeout shouldEqual defaultWriteTimeout
            readTimeout shouldEqual defaultReadTimeout
            logLevel shouldEqual LogLevel.NONE
            hosts shouldEqual applicationID.searchHosts
            defaultHeaders.shouldBeNull()
            engine.shouldBeNull()
            httpClientConfig.shouldBeNull()
        }
    }

    @Test
    fun configurationAnalytics() {
        ConfigurationAnalytics(applicationID, apiKey).apply {
            writeTimeout shouldEqual defaultWriteTimeout
            readTimeout shouldEqual defaultReadTimeout
            logLevel shouldEqual LogLevel.NONE
            hosts shouldEqual analyticsHosts
            defaultHeaders.shouldBeNull()
            engine.shouldBeNull()
            httpClientConfig.shouldBeNull()
        }
    }

    @Test
    fun configurationInsights() {
        ConfigurationInsights(applicationID, apiKey).apply {
            writeTimeout shouldEqual defaultWriteTimeout
            readTimeout shouldEqual defaultReadTimeout
            logLevel shouldEqual LogLevel.NONE
            hosts shouldEqual insightHosts
            defaultHeaders.shouldBeNull()
            engine.shouldBeNull()
            httpClientConfig.shouldBeNull()
        }
    }

    @Test
    fun configurationPlaces() {
        ConfigurationPlaces().apply {
            writeTimeout shouldEqual defaultWriteTimeout
            readTimeout shouldEqual defaultReadTimeout
            logLevel shouldEqual LogLevel.NONE
            hosts shouldEqual placesHosts
            defaultHeaders.shouldBeNull()
            engine.shouldBeNull()
            httpClientConfig.shouldBeNull()
        }
    }
}
