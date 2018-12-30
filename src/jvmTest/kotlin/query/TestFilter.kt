package query

import client.data.Attribute
import client.query.*
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

        "\"attributeA\":\"valueA\"" shouldEqual filter.build()
        "NOT \"attributeA\":\"valueA\"" shouldEqual filterNegate.build()
        "\"attributeA\":\"value with space\"" shouldEqual filterSpace.build()
        "\"attributeA\":\"valueA\"<score=1>" shouldEqual filterScore.build()
    }

    @Test
    fun facetBoolean() {
        val filterTrue = FilterFacet(attributeA, true)
        val filterFalse = FilterFacet(attributeA, false)
        val filterTrueNegate = FilterFacet(attributeA, true).not()
        val filterFalseNegate = FilterFacet(attributeA, false).not()
        val filterScore = FilterFacet(attributeA, true, 4)

        "\"attributeA\":true" shouldEqual filterTrue.build()
        "\"attributeA\":false" shouldEqual filterFalse.build()
        "NOT \"attributeA\":true" shouldEqual filterTrueNegate.build()
        "NOT \"attributeA\":false" shouldEqual filterFalseNegate.build()
        "\"attributeA\":true<score=4>" shouldEqual filterScore.build()
    }

    @Test
    fun facetNumber() {
        val filterInt = FilterFacet(attributeA, 1)
        val filterLong = FilterFacet(attributeA, 1L)
        val filterFloat = FilterFacet(attributeA, 1f)
        val filterDouble = FilterFacet(attributeA, 1.0).not()
        val filterScore = FilterFacet(attributeA, 1, 2)

        "\"attributeA\":1" shouldEqual filterInt.build()
        "\"attributeA\":1" shouldEqual filterLong.build()
        "\"attributeA\":1.0" shouldEqual filterFloat.build()
        "NOT \"attributeA\":1.0" shouldEqual filterDouble.build()
        "\"attributeA\":1<score=2>" shouldEqual filterScore.build()
    }

    @Test
    fun range() {
        val filter = FilterRange(attributeA, 5.0, 6.0)
        val filterNegate = FilterRange(attributeA, 5.0, 6.0).not()

        "\"attributeA\":5.0 TO 6.0" shouldEqual filter.build()
        "NOT \"attributeA\":5.0 TO 6.0" shouldEqual filterNegate.build()
    }

    @Test
    fun tag() {
        val filter = FilterTag("valueA")
        val filterNegate = FilterTag("valueA").not()

        "_tags:\"valueA\"" shouldEqual filter.build()
        "NOT _tags:\"valueA\"" shouldEqual filterNegate.build()
    }

    @Test
    fun comparison() {
        val filter = FilterComparison(attributeA, NumericOperator.Greater, 5.0)
        val filterNegate = FilterComparison(attributeA, NumericOperator.Greater, 5.0).not()

        "\"attributeA\" > 5.0" shouldEqual filter.build()
        "NOT \"attributeA\" > 5.0" shouldEqual filterNegate.build()
    }
}