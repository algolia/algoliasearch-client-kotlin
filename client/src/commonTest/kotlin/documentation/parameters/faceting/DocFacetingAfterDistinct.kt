package documentation.parameters.faceting

import com.algolia.search.dsl.query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocFacetingAfterDistinct {

//    facetingAfterDistinct: Boolean = true|false

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                facetingAfterDistinct = true
            }

            index.search(query)
        }
    }
}
