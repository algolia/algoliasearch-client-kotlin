package dsl.filtering

import attributeA
import attributeB
import com.algolia.search.dsl.filtering.DSLFacetFilters
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup
import shouldEqual
import kotlin.test.Test

internal class TestDSLFacetFilters {

    @Test
    fun and() {
        val dsl = DSLFacetFilters {
            and {
                facet(attributeA, 0)
                facet(attributeA, 1)
            }
        }

        dsl shouldEqual setOf(
            FilterGroup.And.Facet(Filter.Facet(attributeA, 0), Filter.Facet(attributeA, 1))
        )
    }

    @Test
    fun or() {
        val dsl = DSLFacetFilters {
            or {
                facet(attributeA, 0)
                facet(attributeB, 1)
            }
            or {
                facet(attributeA, 0)
            }
        }

        dsl shouldEqual setOf(
            FilterGroup.Or.Facet(Filter.Facet(attributeA, 0), Filter.Facet(attributeB, 1)),
            FilterGroup.Or.Facet(Filter.Facet(attributeA, 0))
        )
    }

    @Test
    fun emptyGroups() {
        val dsl = DSLFacetFilters {
            and { }
            or { }
        }

        dsl shouldEqual setOf()
    }
}
