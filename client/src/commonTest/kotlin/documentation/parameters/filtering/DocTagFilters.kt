package documentation.parameters.filtering

import com.algolia.search.dsl.query
import com.algolia.search.dsl.tagFilters
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocTagFilters {

//    tagFilters {
//        // Declare an OR group for tag filters.
//        or {
//            tag("value")
//
//            tag("value", isNegated = true) // Negate a tag filter
//        }
//        // Declare an AND group for tag filters.
//        and {
//            tag("value")
//        }
//    }

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                tagFilters {
                    or {
                        tag("book")
                        tag("movie")
                    }
                    and {
                        tag("SciFi")
                    }
                }
            }

            index.search(query)
        }
    }
}
