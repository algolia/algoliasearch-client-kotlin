package dsl.filtering

import attributeA
import attributeB
import com.algolia.search.dsl.filtering.DSLNumericFilters
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup
import shouldEqual
import kotlin.test.Test

internal class TestDSLNumericFilters {

    @Test
    fun and() {
        val dsl = DSLNumericFilters {
            and {
                range(attributeA, 0..2)
                range(attributeA, 1..2)
            }
        }

        dsl shouldEqual setOf(
            FilterGroup.And.Numeric(Filter.Numeric(attributeA, 0..2), Filter.Numeric(attributeA, 1..2))
        )
    }

    @Test
    fun or() {
        val dsl = DSLNumericFilters {
            or {
                range(attributeA, 0..2)
                range(attributeB, 1..2)
            }
            or {
                range(attributeA, 0..2)
            }
        }

        dsl shouldEqual setOf(
            FilterGroup.Or.Numeric(Filter.Numeric(attributeA, 0..2), Filter.Numeric(attributeB, 1..2)),
            FilterGroup.Or.Numeric(Filter.Numeric(attributeA, 0..2))
        )
    }

    @Test
    fun emptyGroups() {
        val dsl = DSLNumericFilters {
            and { }
            or { }
        }

        dsl shouldEqual setOf()
    }
}
