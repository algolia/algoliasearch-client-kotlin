package dsl.filtering

import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.NumericOperator
import com.algolia.search.model.filter.not
import com.algolia.search.model.Attribute
import shouldEqual
import kotlin.test.Test


internal class TestFilter {

    private val attributeA = Attribute("attributeA")

    @Test
    fun facetString() {
        val filter = Filter.Facet(attributeA, "valueA")
        val filterNegate = !Filter.Facet(attributeA, "valueA")
        val filterSpace = Filter.Facet(attributeA, "value with space")
        val filterScore = Filter.Facet(attributeA, "valueA", 1)

        filter.build() shouldEqual "\"attributeA\":\"valueA\""
        filterNegate.build() shouldEqual "NOT \"attributeA\":\"valueA\""
        filterSpace.build() shouldEqual "\"attributeA\":\"value with space\""
        filterScore.build() shouldEqual "\"attributeA\":\"valueA\"<score=1>"
    }

    @Test
    fun facetBoolean() {
        val filterTrue = Filter.Facet(attributeA, true)
        val filterFalse = Filter.Facet(attributeA, false)
        val filterTrueNegate = !Filter.Facet(attributeA, true)
        val filterFalseNegate = !Filter.Facet(attributeA, false)
        val filterScore = Filter.Facet(attributeA, true, 4)

        filterTrue.build() shouldEqual "\"attributeA\":true"
        filterFalse.build() shouldEqual "\"attributeA\":false"
        filterTrueNegate.build() shouldEqual "NOT \"attributeA\":true"
        filterFalseNegate.build() shouldEqual "NOT \"attributeA\":false"
        filterScore.build() shouldEqual "\"attributeA\":true<score=4>"
    }

    @Test
    fun facetNumber() {
        val filterInt = Filter.Facet(attributeA, 1)
        val filterLong = Filter.Facet(attributeA, 1L)
        val filterFloat = Filter.Facet(attributeA, 1f)
        val filterDouble = !Filter.Facet(attributeA, 1.0)
        val filterScore = Filter.Facet(attributeA, 1, 2)

        filterInt.build() shouldEqual "\"attributeA\":1"
        filterLong.build() shouldEqual "\"attributeA\":1"
        filterFloat.build() shouldEqual "\"attributeA\":1.0"
        filterDouble.build() shouldEqual "NOT \"attributeA\":1.0"
        filterScore.build() shouldEqual "\"attributeA\":1<score=2>"
    }

    @Test
    fun range() {
        val filterNumericInt = Filter.Numeric(attributeA, 0 until 6)
        val filterNumericLong = Filter.Numeric(attributeA, 0L..6L)
        val filterFloat = Filter.Numeric(attributeA, 0f, 6f)
        val filterDouble = Filter.Numeric(attributeA, 0f, 6f)
        val filterNegate = !Filter.Numeric(attributeA, 0.0, 6.0)

        filterNumericInt.build() shouldEqual "\"attributeA\":0 TO 5"
        filterNumericLong.build() shouldEqual "\"attributeA\":0 TO 6"
        filterFloat.build() shouldEqual "\"attributeA\":0.0 TO 6.0"
        filterDouble.build() shouldEqual "\"attributeA\":0.0 TO 6.0"
        filterNegate.build() shouldEqual "NOT \"attributeA\":0.0 TO 6.0"
    }

    @Test
    fun tag() {
        val filter = Filter.Tag("valueA")
        val filterNegate = !Filter.Tag("valueA")

        filter.build() shouldEqual "_tags:\"valueA\""
        filterNegate.build() shouldEqual "NOT _tags:\"valueA\""
    }

    @Test
    fun comparison() {
        val filter = Filter.Numeric(attributeA, NumericOperator.Greater, 5.0)
        val filterNegate = !Filter.Numeric(attributeA, NumericOperator.Greater, 5.0)

        filter.build() shouldEqual "\"attributeA\" > 5.0"
        filterNegate.build() shouldEqual "NOT \"attributeA\" > 5.0"
    }

    @Test
    fun negation() {
        val filter = Filter.Facet(attributeA, true)

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