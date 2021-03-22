package documentation.methods.advanced

import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocConfigurationTimeout {

//    data class ConfigurationSearch(
//        val applicationID: __ApplicationID__,
//        val apiKey: __APIKey__,
//        val #{writeTimeout}: __Long__ = defaultWriteTimeout,
//        val #{readTimeout}: __Long__ = defaultReadTimeout,
//    ) : Configuration

    @Test
    fun snippet1() {
        val configuration = ConfigurationSearch(
            applicationID = ApplicationID("YourApplicationId"),
            apiKey = APIKey("YourAdminAPIKey"),
            readTimeout = 30,
            writeTimeout = 30
        )
        ClientSearch(configuration)
    }
}
