package documentation.parameters.filtering

import com.algolia.search.dsl.numericFilters
import com.algolia.search.dsl.query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocNumericFilters {

//    numericFilters {
//        and {
//            // "numeric_attribute [= | != | > | >= | < | <=](#numeric-comparisons) numeric_value"
//            comparison("attribute", NumericOperator.Less, 0f)
//            comparison("attribute", NumericOperator.LessOrEquals, 0f)
//            comparison("attribute", NumericOperator.Equals, 0f)
//            comparison("attribute", NotEquals, 0f)
//            comparison("attribute", Greater, 0f)
//            comparison("attribute", GreaterOrEquals, 0f)
//
//            comparison("attribute", GreaterOrEquals, 0f, isNegated = true) // Negate a numeric filter
//        }
//        or {
//            // "attribute:lowerBound [TO](#numeric-range) upperBound"
//            range("attribute", 0..10)
//            range("attribute", 0f, 10f)
//        }
//    }

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                numericFilters {
                    or {
                        comparison("inStock", Equals, 0f)
                        comparison("deliveryDate", Less, 1441755506)
                    }
                    and {
                        comparison("price", Less, 1000)
                    }
                }
            }

            index.search(query)
        }
    }
}
