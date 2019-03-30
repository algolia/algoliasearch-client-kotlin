package dsl.filtering

import com.algolia.search.dsl.filtering.*
import com.algolia.search.model.Attribute
import shouldEqual
import shouldFailWith
import kotlin.test.Test


internal class TestFilter {

    private val attributeA = Attribute("attributeA")

    @Test
    fun facetString() {
        val filter = FilterFacet(attributeA, "valueA")
        val filterNegate = !FilterFacet(attributeA, "valueA")
        val filterSpace = FilterFacet(attributeA, "value with space")
        val filterScore = FilterFacet(attributeA, "valueA", 1)

        filter.build() shouldEqual "\"attributeA\":\"valueA\""
        filterNegate.build() shouldEqual "NOT \"attributeA\":\"valueA\""
        filterSpace.build() shouldEqual "\"attributeA\":\"value with space\""
        filterScore.build() shouldEqual "\"attributeA\":\"valueA\"<score=1>"
    }

    @Test
    fun facetBoolean() {
        val filterTrue = FilterFacet(attributeA, true)
        val filterFalse = FilterFacet(attributeA, false)
        val filterTrueNegate = !FilterFacet(attributeA, true)
        val filterFalseNegate = !FilterFacet(attributeA, false)
        val filterScore = FilterFacet(attributeA, true, 4)

        filterTrue.build() shouldEqual "\"attributeA\":true"
        filterFalse.build() shouldEqual "\"attributeA\":false"
        filterTrueNegate.build() shouldEqual "NOT \"attributeA\":true"
        filterFalseNegate.build() shouldEqual "NOT \"attributeA\":false"
        filterScore.build() shouldEqual "\"attributeA\":true<score=4>"
    }

    @Test
    fun facetNumber() {
        val filterInt = FilterFacet(attributeA, 1)
        val filterLong = FilterFacet(attributeA, 1L)
        val filterFloat = FilterFacet(attributeA, 1f)
        val filterDouble = !FilterFacet(attributeA, 1.0)
        val filterScore = FilterFacet(attributeA, 1, 2)

        filterInt.build() shouldEqual "\"attributeA\":1"
        filterLong.build() shouldEqual "\"attributeA\":1"
        filterFloat.build() shouldEqual "\"attributeA\":1.0"
        filterDouble.build() shouldEqual "NOT \"attributeA\":1.0"
        filterScore.build() shouldEqual "\"attributeA\":1<score=2>"
    }

    @Test
    fun range() {
        val filterRangeInt = FilterRange(attributeA, 0 until 6)
        val filterRangeLong = FilterRange(attributeA, 0L..6L)
        val filterFloat = FilterRange(attributeA, 0f, 6f)
        val filterDouble = FilterRange(attributeA, 0f, 6f)
        val filterNegate = !FilterRange(attributeA, 0.0, 6.0)

        filterRangeInt.build() shouldEqual "\"attributeA\":0 TO 5"
        filterRangeLong.build() shouldEqual "\"attributeA\":0 TO 6"
        filterFloat.build() shouldEqual "\"attributeA\":0.0 TO 6.0"
        filterDouble.build() shouldEqual "\"attributeA\":0.0 TO 6.0"
        filterNegate.build() shouldEqual "NOT \"attributeA\":0.0 TO 6.0"

        shouldFailWith<IllegalArgumentException> { FilterRange(attributeA, 1 until 0) }
    }

    @Test
    fun tag() {
        val filter = FilterTag("valueA")
        val filterNegate = !FilterTag("valueA")

        filter.build() shouldEqual "_tags:\"valueA\""
        filterNegate.build() shouldEqual "NOT _tags:\"valueA\""
    }

    @Test
    fun comparison() {
        val filter = FilterComparison(attributeA, NumericOperator.Greater, 5.0)
        val filterNegate = !FilterComparison(attributeA, NumericOperator.Greater, 5.0)

        filter.build() shouldEqual "\"attributeA\" > 5.0"
        filterNegate.build() shouldEqual "NOT \"attributeA\" > 5.0"
    }

    @Test
    fun negation() {
        val filter = FilterFacet(attributeA, true)

        filter.let {
            it.isNegated shouldEqual false
            it.build() shouldEqual "\"attributeA\":true"
        }
        (!filter).let {
            it.isNegated shouldEqual true
            it.build() shouldEqual "NOT \"attributeA\":true"
        }
        (!filter).let {
            it.isNegated shouldEqual false
            it.build() shouldEqual "\"attributeA\":true"
        }
    }
}