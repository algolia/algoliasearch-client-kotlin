package documentation.parameters.faceting

import com.algolia.search.dsl.query
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocFacetingAfterDistinct {

//    facetingAfterDistinct: Boolean = true|false

    @Test
    fun snippet1() {
        runBlocking {
            val query = query("query") {
                facetingAfterDistinct = true
            }

            index.search(query)
        }
    }
}
