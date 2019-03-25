package snippets.advanced

import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import kotlin.test.Test


internal class SnippetConfigurationTimeout {

//    data class ConfigurationSearch(
//        val applicationID: __ApplicationID__,
//        val apiKey: __APIKey__,
//        val #{writeTimeout}: __Long__ = defaultWriteTimeout,
//        val #{readTimeout}: __Long__ = defaultReadTimeout,
//    ) : Configuration

    @Test
    fun configurationTimeout() {
        val configuration = ConfigurationSearch(
            apiKey = APIKey("YourApplicationId"),
            applicationID = ApplicationID("YourAPIKey"),
            readTimeout = 30,
            writeTimeout = 30
        )
        ClientSearch(configuration)
    }
}