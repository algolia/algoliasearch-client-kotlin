package documentation.methods.advanced

import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocConfigurationHeaders {

//    data class ConfigurationSearch(
//        val applicationID: __ApplicationID__,
//        val apiKey: __APIKey__,
//        val defaultHeaders: __MutableMap<String, String>__ = mutableMapOf()
//    ) : Configuration

    @Test
    fun snippet1() {
        val configuration = ConfigurationSearch(
            applicationID = ApplicationID("YourApplicationId"),
            apiKey = APIKey("YourAdminAPIKey"),
            defaultHeaders = mapOf("NAME-OF-HEADER" to "value-of-header")
        )
        ClientSearch(configuration)
    }
}
