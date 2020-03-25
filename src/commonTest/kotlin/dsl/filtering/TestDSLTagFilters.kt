package dsl.filtering

import attributeA
import attributeB
import com.algolia.search.dsl.filtering.DSLTagFilters
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup
import shouldEqual
import kotlin.test.Test

internal class TestDSLTagFilters {

    @Test
    fun and() {
        val dsl = DSLTagFilters {
            and {
                tag(attributeA.raw)
                tag(attributeB.raw)
            }
        }

        dsl shouldEqual setOf(
            FilterGroup.And.Tag(Filter.Tag(attributeA.raw), Filter.Tag(attributeB.raw))
        )
    }

    @Test
    fun or() {
        val dsl = DSLTagFilters {
            or {
                tag(attributeA.raw)
                tag(attributeB.raw)
            }
            or {
                tag(attributeA.raw)
            }
        }

        dsl shouldEqual setOf(
            FilterGroup.Or.Tag(Filter.Tag(attributeA.raw), Filter.Tag(attributeB.raw)),
            FilterGroup.Or.Tag(Filter.Tag(attributeA.raw))
        )
    }

    @Test
    fun emptyGroups() {
        val dsl = DSLTagFilters {
            and { }
            or { }
        }

        dsl shouldEqual setOf()
    }
}
