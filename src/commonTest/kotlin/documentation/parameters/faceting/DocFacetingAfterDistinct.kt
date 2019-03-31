package documentation.parameters.faceting

import com.algolia.search.dsl.query
import runBlocking
import documentation.index
import kotlin.test.Test


internal class DocFacetingAfterDistinct {

//    facetingAfterDistinct: Boolean = true|false

    @Test
    fun snippet() {
        runBlocking {
            val query = query {
                facetingAfterDistinct = true
            }

            index.search(query)
        }
    }
}