package documentation

import clientSearch
import com.algolia.search.model.IndexName

internal val client get() = clientSearch
internal val indexName = IndexName("doc")
internal val index get() = client.initIndex(indexName)
