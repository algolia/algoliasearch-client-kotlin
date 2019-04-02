package documentation.parameters.filtering

import com.algolia.search.dsl.facetFilters
import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocFacetFilters {

//    facetFilters {
//        // Declare an OR group for facet filters.
//        or {
//            +facet("attribute", "value")
//            +facet("attribute", 0)
//            +facet("attribute", true)
//        }
//        // Declare an AND group for facet filters.
//        and {
//            +facet("attribute", "value")
//            +facet("attribute", true)
//            +facet("attribute", 0)
//        }
//    }

    @Test
    fun singleFacet() {
        runBlocking {
            val query = query("query") {
                facetFilters {
                    +and { +facet("category", "Book") }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun andFilter() {
        runBlocking {
            val query = query("query") {
                facetFilters {
                    +and {
                        +facet("category", "Book")
                        +facet("author", "John Doe")
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun orFilter() {
        runBlocking {
            val query = query("query") {
                facetFilters {
                    +or {
                        +facet("category", "Book")
                        +facet("category", "Movie")
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun combination() {
        runBlocking {
            val query = query("query") {
                facetFilters {
                    +or {
                        +facet("category", "Book")
                        +facet("category", "Movie")
                    }
                    +and {
                        +facet("author", "John Doe")
                    }
                }
            }

            index.search(query)
        }
    }
}