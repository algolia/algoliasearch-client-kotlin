package documentation.guides.results

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import documentation.client
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Suppress("ConstantConditionIf")
@Ignore
internal class GuideSearchReplicas {

    @Test
    fun snippet() {
        runBlocking {
            val sortByPrice = false
            val indexName = if (sortByPrice) "products_price_desc" else "products"
            val index = client.initIndex(IndexName(indexName))

            index.search(Query("phone"))
        }
    }
}