package documentation.parameters.geosearch

import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.dsl.query
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocAroundLatLngViaIP {

//    aroundLatLngViaIP: Boolean = true|false

    @Test
    fun snippet1() {
        runTest {
            val configuration = ConfigurationSearch(
                ApplicationID("YourApplicationID"),
                APIKey("YourSearchOnlyAPIKey"),
                defaultHeaders = mapOf("X-Forwarded-For" to "94.228.178.246")
            )
            val client = ClientSearch(configuration)
            val index = client.initIndex(IndexName("index_name"))

            val query = query("query") {
                aroundLatLngViaIP = true
            }

            index.search(query)
        }
    }
}
