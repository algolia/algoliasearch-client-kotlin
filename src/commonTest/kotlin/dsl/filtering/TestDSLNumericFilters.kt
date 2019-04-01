package dsl.filtering

import attributeA
import attributeB
import com.algolia.search.dsl.filtering.DSLNumericFilters
import com.algolia.search.dsl.filtering.FilterRange
import com.algolia.search.dsl.filtering.GroupAnd
import com.algolia.search.dsl.filtering.GroupOr
import shouldEqual
import kotlin.test.Test


internal class TestDSLNumericFilters {

    @Test
    fun and() {
        val dsl = DSLNumericFilters {
            and {
                +range(attributeA, 0..2)
                +range(attributeA, 1..2)
            }
        }

        dsl shouldEqual listOf(
            GroupAnd.Numeric(FilterRange(attributeA, 0..2), FilterRange(attributeA, 1..2))
        )
    }

    @Test
    fun or() {
        val dsl = DSLNumericFilters {
            or {
                +range(attributeA, 0..2)
                +range(attributeB, 1..2)
            }
            or {
                +range(attributeA, 0..2)
            }
        }

        dsl shouldEqual listOf(
            GroupOr.Numeric(FilterRange(attributeA, 0..2), FilterRange(attributeB, 1..2)),
            GroupOr.Numeric(FilterRange(attributeA, 0..2))
        )
    }

    @Test
    fun emptyGroups() {
        val dsl = DSLNumericFilters {
            and { }
            or { }
        }

        dsl shouldEqual listOf()
    }
}