package documentation.parameters.faceting

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Test


internal class DocFacetingAfterDistinct {

//    facetingAfterDistinct: Boolean = true|false

    @Test
    fun query() {
        runBlocking {
            val query = query {
                facetingAfterDistinct = true
            }

            index.search(query)
        }
    }
}