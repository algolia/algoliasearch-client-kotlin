package dsl.filtering

import attributeA
import com.algolia.search.dsl.filtering.DSLGroupFacet
import com.algolia.search.dsl.filtering.FilterFacet
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestDSLGroupFacet {

    @Test
    fun string() {
        val dsl = DSLGroupFacet {
            +facet(attributeA.raw, 0)
            +facet(attributeA.raw, unknown)
            +facet(attributeA.raw, true, 0)
        }

        dsl shouldEqual setOf(
            FilterFacet(attributeA, 0),
            FilterFacet(attributeA, unknown),
            FilterFacet(attributeA, true, 0)
        )
    }

    @Test
    fun attribute() {
        val dsl = DSLGroupFacet {
            +facet(attributeA, 0)
            +facet(attributeA, unknown)
            +facet(attributeA, true, 0)
        }

        dsl shouldEqual setOf(
            FilterFacet(attributeA, 0),
            FilterFacet(attributeA, unknown),
            FilterFacet(attributeA, true, 0)
        )
    }
}