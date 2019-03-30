package snippets.parameters.filtering

import com.algolia.search.dsl.optionalFilters
import com.algolia.search.dsl.query
import runBlocking
import snippets.index
import kotlin.test.Test


internal class SnippetOptionalFilters {

//    optionalFilters {
//        // Declare an OR group for optional facet filters.
//        or {
//            +facet("attribute", "value")
//            +facet("attribute", 0)
//            +facet("attribute", true)
//        }
//        // Declare an AND group for optional facet filters.
//        and {
//            +facet("attribute", "value")
//            +facet("attribute", true)
//            +facet("attribute", 0)
//        }
//    }

    @Test
    fun snippet() {
        runBlocking {
            val query = query {
                optionalFilters {
                    and {
                        +facet("category", "Book")
                        +facet("author", "John Doe")
                    }
                }
            }

            index.search(query)
        }
    }
}