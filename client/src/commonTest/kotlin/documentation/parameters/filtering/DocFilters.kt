package documentation.parameters.filtering

import com.algolia.search.dsl.filters
import com.algolia.search.dsl.query
import com.algolia.search.model.filter.NumericOperator
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocFilters {

//    filters {
//        // Declare an [OR](#boolean-operators) group for facet filters.
//        orFacet {
//            // "[attribute:value](#facet-filters)"
//            facet("attribute", "value")
//            facet("attribute", 0)
//            facet("attribute", true)
//
//            facet("attribute", "value", isNegated = true) // Negate a filter
//        }
//        // Declare an [OR](#boolean-operators) group for tag filters.
//        orTag {
//            // "[_tags](#tag-filters):value"
//            tag("value")
//        }
//        // Declare an [OR](#boolean-operators) group for numeric filters.
//        orNumeric {
//            // "attribute:lowerBound [TO](#numeric-range) upperBound"
//            range("attribute", 0..10)
//            range("attribute", 0f, 10f)
//            // "numeric_attribute [= | != | > | >= | < | <=](#numeric-comparisons) numeric_value"
//            comparison("attribute", Less, 0f)
//            comparison("attribute", LessOrEquals, 0f)
//            comparison("attribute", Equals, 0f)
//            comparison("attribute", NotEquals, 0f)
//            comparison("attribute", Greater, 0f)
//            comparison("attribute", GreaterOrEquals, 0f)
//        }
//        // Declare an [AND](#boolean-operators) group for any type of filters.
//        and {
//            // "[attribute:value](#facet-filters)"
//            facet("attribute", "value")
//            facet("attribute", true)
//            facet("attribute", 0)
//            tag("value")
//            range("attribute", 0..10)
//            comparison("attribute", NumericOperator.Less, 0f)
//        }
//    }

    @Test
    fun snippet1() {
        runTest {
            // "(category:Book OR category:Ebook) AND _tags:published"
            val query = query("query") {
                filters {
                    orFacet {
                        facet("category", "Book")
                        facet("category", "Ebook")
                    }
                    and {
                        tag("published")
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            //  available = 1
            //  AND (category:Book OR NOT category:Ebook)
            //  AND _tags:published
            //  AND publication_date:1441745506 TO 1441755506
            //  AND inStock > 0
            //  AND author:\"John Doe\"";
            val query = query("query") {
                filters {
                    and {
                        comparison("available", Equals, 1)
                        tag("published")
                        range("publication_date", 1441745506..1441755506)
                        comparison("inStock", NumericOperator.Greater, 0)
                        facet("author", "John Doe")
                    }
                    orFacet {
                        facet("category", "Book")
                        facet("category", "EBook", isNegated = true)
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun snippet3() {
        runTest {
            // "\"category\":\"Books and Comics\""
            val query = query("query") {
                filters {
                    and {
                        // You don't have to escape strings, it is done for you.
                        facet("category", "Books and Comics")
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun snippet4() {
        runTest {
            // "\"keyword\":\"OR\""
            val query = query("query") {
                filters {
                    and {
                        // You don't have to escape keywords, it is done for you.
                        facet("keyword", "OR")
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun snippet5() {
        runTest {
            // "\"content\":\"It's a wonderful day\""
            val query = query("query") {
                filters {
                    and {
                        // You don't have to escape single quotes, it is done for you.
                        facet("content", "It's a wonderful day")
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun snippet6() {
        runTest {
            // "\"content\":\"She said Hello World\""
            val query = query("query") {
                filters {
                    and {
                        facet("content", "She said \"Hello World\"")
                    }
                }
            }

            index.search(query)
        }
    }
}
