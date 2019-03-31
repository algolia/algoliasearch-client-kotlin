package documentation.methods.advanced

import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import kotlin.test.Test


internal class DocConfigurationHeaders {

//    data class ConfigurationSearch(
//        val applicationID: __ApplicationID__,
//        val apiKey: __APIKey__,
//        val defaultHeaders: __MutableMap<String, String>__ = mutableMapOf()
//    ) : Configuration

    @Test
    fun configurationHeaders() {
        val configuration = ConfigurationSearch(
            apiKey = APIKey("YourApplicationId"),
            applicationID = ApplicationID("YourAPIKey"),
            defaultHeaders = mapOf("NAME-OF-HEADER" to "value-of-header")
        )
        ClientSearch(configuration)
    }
}