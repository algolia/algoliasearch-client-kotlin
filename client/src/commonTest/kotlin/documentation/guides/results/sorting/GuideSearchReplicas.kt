package documentation.guides.results.sorting

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import documentation.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Suppress("ConstantConditionIf")
@Ignore
internal class GuideSearchReplicas {

    @Test
    fun snippet1() {
        runTest {
            val sortByPrice = false
            val indexName = if (sortByPrice) "products_price_desc" else "products"
            val index = client.initIndex(IndexName(indexName))

            index.search(Query("phone"))
        }
    }
}
