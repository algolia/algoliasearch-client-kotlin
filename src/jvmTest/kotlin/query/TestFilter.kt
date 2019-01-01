package query

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.query.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
class TestFilter {

    private val attributeA = Attribute("attributeA")

    @Test
    fun facetString() {
        val filter = FilterFacet(attributeA, "valueA")
        val filterNegate = FilterFacet(attributeA, "valueA").not()
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
        val filterTrueNegate = FilterFacet(attributeA, true).not()
        val filterFalseNegate = FilterFacet(attributeA, false).not()
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
        val filterDouble = FilterFacet(attributeA, 1.0).not()
        val filterScore = FilterFacet(attributeA, 1, 2)

        filterInt.build() shouldEqual "\"attributeA\":1"
        filterLong.build() shouldEqual "\"attributeA\":1"
        filterFloat.build() shouldEqual "\"attributeA\":1.0"
        filterDouble.build() shouldEqual "NOT \"attributeA\":1.0"
        filterScore.build() shouldEqual "\"attributeA\":1<score=2>"
    }

    @Test
    fun range() {
        val filter = FilterRange(attributeA, 5.0, 6.0)
        val filterNegate = FilterRange(attributeA, 5.0, 6.0).not()

        filter.build() shouldEqual "\"attributeA\":5.0 TO 6.0"
        filterNegate.build() shouldEqual "NOT \"attributeA\":5.0 TO 6.0"
    }

    @Test
    fun tag() {
        val filter = FilterTag("valueA")
        val filterNegate = FilterTag("valueA").not()

        filter.build() shouldEqual "_tags:\"valueA\""
        filterNegate.build() shouldEqual "NOT _tags:\"valueA\""
    }

    @Test
    fun comparison() {
        val filter = FilterComparison(attributeA, NumericOperator.Greater, 5.0)
        val filterNegate = FilterComparison(attributeA, NumericOperator.Greater, 5.0).not()

        filter.build() shouldEqual "\"attributeA\" > 5.0"
        filterNegate.build() shouldEqual "NOT \"attributeA\" > 5.0"
    }
}