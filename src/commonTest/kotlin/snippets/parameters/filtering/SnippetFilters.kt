package snippets.parameters.filtering

import com.algolia.search.dsl.filtering.NumericOperator
import com.algolia.search.dsl.filtering.not
import com.algolia.search.dsl.filters
import com.algolia.search.dsl.query
import runBlocking
import snippets.index
import kotlin.test.Test


internal class SnippetFilters {

    @Test
    fun parameter() {
        query {
            filters {
                // Declare an [OR](#boolean-operators) group for facet filters.
                orFacet {
                    // "[facetName:facetValue](#facet-filters)"
                    +facet("attribute", "value")
                    +facet("attribute", 0)
                    +facet("attribute", true)
                }
                // Declare an [OR](#boolean-operators) group for tag filters.
                orTag {
                    // "[_tags](#tag-filters):value"
                    +tag("value")
                }
                // Declare an [OR](#boolean-operators) group for numeric filters.
                orNumeric {
                    // "attribute:lowerBound [TO](#numeric-range) upperBound"
                    +range("attribute", 0..10)
                    +range("attribute", 0f, 10f)
                    // "numeric_attribute [= | != | > | >= | < | <=](#numeric-comparisons) numeric_value"
                    +comparison("attribute", Lesser, 0f)
                    +comparison("attribute", LesserOrEquals, 0f)
                    +comparison("attribute", Equals, 0f)
                    +comparison("attribute", NotEquals, 0f)
                    +comparison("attribute", Greater, 0f)
                    +comparison("attribute", GreaterOrEquals, 0f)
                }
                // Declare an [AND](#boolean-operators) group for any type of filters.
                and {
                    // "[facetName:facetValue](#facet-filters)"
                    +facet("attribute", "value")
                    +facet("attribute", true)
                    +facet("attribute", 0)
                    +tag("value")
                    +range("attribute", 0..10)
                    +comparison("attribute", Lesser, 0f)
                }
            }
        }
    }

    @Test
    fun searchQuery() {
        runBlocking {
            // "(category:Book OR category:Ebook) AND _tags:published"
            val query = query {
                query = "query"
                filters {
                    orFacet {
                        +facet("category", "Book")
                        +facet("category", "Ebook")
                    }
                    and {
                        +tag("published")
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun complexFilters() {
        runBlocking {
            //  available = 1
            //  AND (category:Book OR NOT category:Ebook)
            //  AND _tags:published
            //  AND publication_date:1441745506 TO 1441755506
            //  AND inStock > 0
            //  AND author:\"John Doe\"";
            val query = query {
                query = ""
                filters {
                    and {
                        +comparison("available", Equals, 1)
                        +tag("published")
                        +range("publication_date", 1441745506..1441755506)
                        +comparison("inStock", NumericOperator.Greater, 0)
                        +facet("author", "John Doe")
                    }
                    orFacet {
                        +facet("category", "Book")
                        +!facet("category", "EBook")
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun handleSpaces() {
        runBlocking {
            // "\"category\":\"Books and Comics\""
            val query = query {
                query = "query"
                filters {
                    and {
                        +facet("category", "Books and Comics")
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun conflictingKeyword() {
        runBlocking {
            // "\"keyword\":\"OR\""
            val query = query {
                query = "query"
                filters {
                    and {
                        +facet("keyword", "OR")
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun singleQuotes() {
        runBlocking {
            // "\"content\":\"It's a wonderful day\""
            val query = query {
                query = "query"
                filters {
                    and {
                        +facet("content", "It's a wonderful day")
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun doubleQuotes() {
        runBlocking {
            // "\"content\":\"She said Hello World\""
            val query = query {
                query = "query"
                filters {
                    and {
                        +facet("content", "She said Hello World")
                    }
                }
            }

            index.search(query)
        }
    }
}