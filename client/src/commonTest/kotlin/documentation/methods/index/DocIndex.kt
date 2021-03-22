package documentation.methods.index

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocIndex {

    @Test
    fun snippet1() {
        val client = ClientSearch(
            applicationID = ApplicationID("latency"),
            apiKey = APIKey("YourAdminAPIKey")
        )
        val indexName = IndexName("your_index_name")

        client.initIndex(indexName)
    }
}
