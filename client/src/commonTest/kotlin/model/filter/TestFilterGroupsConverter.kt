package model.filter

import attributeA
import attributeB
import com.algolia.search.dsl.facetFilters
import com.algolia.search.dsl.filters
import com.algolia.search.dsl.numericFilters
import com.algolia.search.dsl.query
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestFilterGroupsConverter {

    @Test
    fun notInRangeSQL() {
        val query = query {
            filters {
                and { range(attributeA, 0..10, isNegated = true) }
            }
        }
        query.filters shouldEqual "(NOT \"attributeA\":0 TO 10)"
    }

    @Test
    fun notIntRangeLegacy() {
        val query = query {
            numericFilters {
                and { range(attributeA, 0..10, isNegated = true) }
            }
        }
        query.numericFilters shouldEqual listOf(listOf("attributeA < 0"), listOf("attributeA > 10"))
    }

    @Test
    fun legacy() {
        val query = query {
            facetFilters {
                and {
                    facet(attributeA, unknown)
                    facet(attributeB, unknown)
                }
                or {
                    facet(attributeA, unknown)
                    facet(attributeB, unknown)
                }
            }
        }

        query.facetFilters shouldEqual listOf(
            listOf("attributeA:unknown"),
            listOf("attributeB:unknown"),
            listOf("attributeA:unknown", "attributeB:unknown")
        )
    }
}
