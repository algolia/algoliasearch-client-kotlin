package documentation

import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocMultiCluster {

    @Test
    fun snippet1() {
        runTest {
            val client = ClientSearch(
                ConfigurationSearch(
                    applicationID = ApplicationID("Your Application ID"),
                    apiKey = APIKey("Your APP ID"),
                    defaultHeaders = mapOf("X-Algolia-User-ID" to "user123")
                )
            )
            val index = client.initIndex(IndexName("index_name"))

            index.search(Query("query"))
        }
    }
}
