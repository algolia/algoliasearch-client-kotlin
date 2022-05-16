package documentation.parameters.advanced

import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.dsl.query
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocAnalytics {

//    analytics: Boolean = true|false

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                analytics = true
            }

            index.search(query)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            // "94.228.178.246" should be replaced with your end user IP.
            // Depending on your stack there are multiple ways to get this information.
            val client = ClientSearch(
                ConfigurationSearch(
                    applicationID = ApplicationID("Your Application ID"),
                    apiKey = APIKey("Your APP ID"),
                    defaultHeaders = mapOf("X-Forwarded-For" to "94.228.178.246")
                )
            )
            val index = client.initIndex(IndexName("index_name"))

            val query = query("query") {
                analytics = true
            }

            index.search(query)
        }
    }
}
