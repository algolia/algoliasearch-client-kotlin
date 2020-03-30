package documentation.guides.results.sorting

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import documentation.client
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Suppress("ConstantConditionIf")
@Ignore
internal class GuideSearchReplicas {

    @Test
    fun snippet1() {
        runBlocking {
            val sortByPrice = false
            val indexName = if (sortByPrice) "products_price_desc" else "products"
            val index = client.initIndex(IndexName(indexName))

            index.search(Query("phone"))
        }
    }
}
