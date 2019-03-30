package dsl.filter

import attributeA
import com.algolia.search.dsl.filter.*
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestDSLGroupFilter {

    @Test
    fun dx() {
        val dsl = DSLGroupFilter().apply {
            +tag(unknown)
            +facet(attributeA, 0)
            +range(attributeA, 0 until 2)
            +comparison(attributeA, LesserOrEquals, 0)
        }

        dsl.build() shouldEqual setOf(
            FilterTag(unknown),
            FilterFacet(attributeA, 0),
            FilterRange(attributeA, 0 until 2),
            FilterComparison(attributeA, NumericOperator.LesserOrEquals, 0)
        )
    }
}