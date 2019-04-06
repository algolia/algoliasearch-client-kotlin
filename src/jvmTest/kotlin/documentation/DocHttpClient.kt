package documentation

import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.apache.Apache
import io.ktor.client.engine.okhttp.OkHttp
import org.junit.Test


internal class DocHttpClient {

    @Test
    fun default() {
        ClientSearch(
            applicationID = ApplicationID("Your Application ID"),
            apiKey = APIKey("Your API key")
        )
    }

    @Test
    fun apache() {
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
    fun okHttp() {
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

    @Test
    fun android() {
        ClientSearch(
            ConfigurationSearch(
                applicationID = ApplicationID("Your Application ID"),
                apiKey = APIKey("Your API key"),
                engine = Android.create {
                    // Pass additional configuration here.
                }
            )
        )
    }
}