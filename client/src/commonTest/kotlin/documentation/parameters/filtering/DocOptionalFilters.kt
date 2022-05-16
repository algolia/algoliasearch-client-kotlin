package documentation.parameters.filtering

import com.algolia.search.dsl.optionalFilters
import com.algolia.search.dsl.query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocOptionalFilters {

//    optionalFilters {
//        // Declare an OR group for optional facet filters.
//        or {
//            facet("attribute", "value")
//            facet("attribute", 0)
//            facet("attribute", true)
//
//            facet("attribute", "value", isNegated = true) // Negate a facet filter
//        }
//        // Declare an AND group for optional facet filters.
//        and {
//            facet("attribute", "value")
//            facet("attribute", true)
//            facet("attribute", 0)
//        }
//    }

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                optionalFilters {
                    and {
                        facet("category", "Book")
                        facet("author", "John Doe")
                    }
                }
            }

            index.search(query)
        }
    }
}
