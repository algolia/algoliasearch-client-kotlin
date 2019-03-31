package documentation.parameters.filtering

import com.algolia.search.dsl.numericFilters
import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Test


internal class DocNumericFilters {

//    numericFilters {
//        and {
//            // "numeric_attribute [= | != | > | >= | < | <=](#numeric-comparisons) numeric_value"
//            +comparison("attribute", NumericOperator.Lesser, 0f)
//            +comparison("attribute", NumericOperator.LesserOrEquals, 0f)
//            +comparison("attribute", NumericOperator.Equals, 0f)
//            +comparison("attribute", NotEquals, 0f)
//            +comparison("attribute", Greater, 0f)
//            +comparison("attribute", GreaterOrEquals, 0f)
//        }
//        or {
//            // "attribute:lowerBound [TO](#numeric-range) upperBound"
//            +range("attribute", 0..10)
//            +range("attribute", 0f, 10f)
//        }
//    }

    @Test
    fun query() {
        runBlocking {
            val query = query {
                numericFilters {
                    or {
                        +comparison("inStock", Equals, 0f)
                        +comparison("deliveryDate", Lesser, 1441755506)
                    }
                    and {
                        +comparison("price", Lesser, 1000)
                    }
                }
            }

            index.search(query)
        }
    }
}