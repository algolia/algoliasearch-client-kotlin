package snippets.methods.index

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import kotlin.test.Test


class SnippetIndex {

    @Test
    fun index() {
        val client = ClientSearch(
            applicationID = ApplicationID("latency"),
            apiKey = APIKey("YourAPIKey")
        )
        val indexName = IndexName("your_index_name")

        client.initIndex(indexName)
    }
}