package documentation

import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import io.ktor.client.engine.apache.Apache
import io.ktor.client.engine.okhttp.OkHttp
import org.junit.Ignore
import org.junit.Test

@Ignore
internal class DocHttpClient {

    @Test
    fun snippet1() {
        ClientSearch(
            applicationID = ApplicationID("Your Application ID"),
            apiKey = APIKey("Your API key")
        )
    }

    @Test
    fun snippet2() {
        ClientSearch(
            ConfigurationSearch(
                applicationID = ApplicationID("Your Application ID"),
                apiKey = APIKey("Your API key"),
                engine = Apache.create {
                    // Pass additional configuration here.
                }
            )
        )
    }

    @Test
    fun snippet3() {
        ClientSearch(
            ConfigurationSearch(
                applicationID = ApplicationID("Your Application ID"),
                apiKey = APIKey("Your API key"),
                engine = OkHttp.create {
                    // Pass additional configuration here.
                }
            )
        )
    }
}
