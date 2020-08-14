package configuration

import BuildConfig
import com.algolia.search.configuration.AlgoliaSearchClient
import com.algolia.search.configuration.ConfigurationAnalytics
import com.algolia.search.configuration.ConfigurationInsights
import com.algolia.search.configuration.ConfigurationPlaces
import com.algolia.search.configuration.ConfigurationRecommendation
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.Region
import com.algolia.search.configuration.internal.DEFAULT_READ_TIMEOUT
import com.algolia.search.configuration.internal.DEFAULT_WRITE_TIMEOUT
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.internal.hosts
import com.algolia.search.transport.internal.insightHosts
import com.algolia.search.transport.internal.placesHosts
import com.algolia.search.transport.internal.searchHosts
import io.ktor.client.features.logging.LogLevel
import shouldBeNull
import shouldEqual
import kotlin.test.Test

internal class TestConfiguration {

    private val applicationID = ApplicationID("applicationID")
    private val apiKey = APIKey("apiKey")
    private val regionRecommendation = Region.Recommendation.EU
    private val regionAnalytics = Region.Analytics.EU

    @Test
    fun testAlgoliaSearchClient() {
        AlgoliaSearchClient.version shouldEqual BuildConfig.version
    }

    @Test
    fun configurationSearch() {
        ConfigurationSearch(applicationID, apiKey).apply {
            writeTimeout shouldEqual DEFAULT_WRITE_TIMEOUT
            readTimeout shouldEqual DEFAULT_READ_TIMEOUT
            logLevel shouldEqual LogLevel.NONE
            hosts shouldEqual applicationID.searchHosts
            defaultHeaders.shouldBeNull()
            engine.shouldBeNull()
            httpClientConfig.shouldBeNull()
        }
    }

    @Test
    fun configurationAnalytics() {
        ConfigurationAnalytics(applicationID, apiKey, regionAnalytics).apply {
            writeTimeout shouldEqual DEFAULT_WRITE_TIMEOUT
            readTimeout shouldEqual DEFAULT_READ_TIMEOUT
            logLevel shouldEqual LogLevel.NONE
            hosts shouldEqual regionAnalytics.hosts
            defaultHeaders.shouldBeNull()
            engine.shouldBeNull()
            httpClientConfig.shouldBeNull()
        }
    }

    @Test
    fun configurationInsights() {
        ConfigurationInsights(applicationID, apiKey).apply {
            writeTimeout shouldEqual DEFAULT_WRITE_TIMEOUT
            readTimeout shouldEqual DEFAULT_READ_TIMEOUT
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
            writeTimeout shouldEqual DEFAULT_WRITE_TIMEOUT
            readTimeout shouldEqual DEFAULT_READ_TIMEOUT
            logLevel shouldEqual LogLevel.NONE
            hosts shouldEqual placesHosts
            defaultHeaders.shouldBeNull()
            engine.shouldBeNull()
            httpClientConfig.shouldBeNull()
        }
    }

    @Test
    fun configurationRecommendation() {
        ConfigurationRecommendation(applicationID, apiKey, regionRecommendation).apply {
            writeTimeout shouldEqual DEFAULT_WRITE_TIMEOUT
            readTimeout shouldEqual DEFAULT_READ_TIMEOUT
            logLevel shouldEqual LogLevel.NONE
            hosts shouldEqual regionRecommendation.hosts
            defaultHeaders.shouldBeNull()
            engine.shouldBeNull()
            httpClientConfig.shouldBeNull()
        }
    }
}
