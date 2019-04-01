package dsl.filtering

import attributeA
import attributeB
import com.algolia.search.dsl.filtering.DSLTagFilters
import com.algolia.search.dsl.filtering.FilterTag
import com.algolia.search.dsl.filtering.GroupAnd
import com.algolia.search.dsl.filtering.GroupOr
import shouldEqual
import kotlin.test.Test


internal class TestDSLTagFilters {

    @Test
    fun and() {
        val dsl = DSLTagFilters {
            and {
                +tag(attributeA.raw)
                +tag(attributeB.raw)
            }
        }

        dsl shouldEqual listOf(
            GroupAnd.Tag(FilterTag(attributeA.raw), FilterTag(attributeB.raw))
        )
    }

    @Test
    fun or() {
        val dsl = DSLTagFilters {
            or {
                +tag(attributeA.raw)
                +tag(attributeB.raw)
            }
            or {
                +tag(attributeA.raw)
            }
        }

        dsl shouldEqual listOf(
            GroupOr.Tag(FilterTag(attributeA.raw), FilterTag(attributeB.raw)),
            GroupOr.Tag(FilterTag(attributeA.raw))
        )
    }

    @Test
    fun emptyGroups() {
        val dsl = DSLTagFilters {
            and { }
            or { }
        }

        dsl shouldEqual listOf()
    }
}