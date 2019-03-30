package snippets.parameters.faceting

import com.algolia.search.dsl.query
import runBlocking
import snippets.index
import kotlin.test.Test


internal class SnippetFacetingAfterDistinct {

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