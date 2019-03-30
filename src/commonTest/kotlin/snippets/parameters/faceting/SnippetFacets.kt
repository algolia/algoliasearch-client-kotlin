package snippets.parameters.faceting

import com.algolia.search.dsl.facets
import com.algolia.search.dsl.query
import runBlocking
import snippets.index
import kotlin.test.Test


internal class SnippetFacets {

    @Test
    fun parameter() {
        query {
            facets {
                +"attribute"
            }
        }
    }

    @Test
    fun snippet() {
        runBlocking {
            val query = query {
                query = "query"
                facets {
                    +"category"
                    +"author"
                }
            }

            index.search(query)
        }
    }
}