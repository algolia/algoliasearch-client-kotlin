package query

import client.data.Attribute
import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestFilter {

    private val attributeA = Attribute("attributeA")

    @Test
    fun facetString() {
        val filter = FilterFacet(attributeA, "valueA")
        val filterNegate = FilterFacet(attributeA, "valueA").not()
        val filterSpace = FilterFacet(attributeA, "value with space")
        val filterScore = FilterFacet(attributeA, "valueA", 1)

        assertEquals("\"attributeA\":\"valueA\"", filter.build())
        assertEquals("NOT \"attributeA\":\"valueA\"", filterNegate.build())
        assertEquals("\"attributeA\":\"value with space\"", filterSpace.build())
        assertEquals("\"attributeA\":\"valueA\"<score=1>", filterScore.build())
    }

    @Test
    fun facetBoolean() {
        val filterTrue = FilterFacet(attributeA, true)
        val filterFalse = FilterFacet(attributeA, false)
        val filterTrueNegate = FilterFacet(attributeA, true).not()
        val filterFalseNegate = FilterFacet(attributeA, false).not()
        val filterScore = FilterFacet(attributeA, true, 4)

        assertEquals("\"attributeA\":true", filterTrue.build())
        assertEquals("\"attributeA\":false", filterFalse.build())
        assertEquals("NOT \"attributeA\":true", filterTrueNegate.build())
        assertEquals("NOT \"attributeA\":false", filterFalseNegate.build())
        assertEquals("\"attributeA\":true<score=4>", filterScore.build())
    }

    @Test
    fun facetNumber() {
        val filterInt = FilterFacet(attributeA, 1)
        val filterLong = FilterFacet(attributeA, 1L)
        val filterFloat = FilterFacet(attributeA, 1f)
        val filterDouble = FilterFacet(attributeA, 1.0).not()
        val filterScore = FilterFacet(attributeA, 1, 2)

        assertEquals("\"attributeA\":1", filterInt.build())
        assertEquals("\"attributeA\":1", filterLong.build())
        assertEquals("\"attributeA\":1.0", filterFloat.build())
        assertEquals("NOT \"attributeA\":1.0", filterDouble.build())
        assertEquals("\"attributeA\":1<score=2>", filterScore.build())
    }

    @Test
    fun range() {
        val filter = FilterRange(attributeA, 5.0, 6.0)
        val filterNegate = FilterRange(attributeA, 5.0, 6.0).not()

        assertEquals("\"attributeA\":5.0 TO 6.0", filter.build())
        assertEquals("NOT \"attributeA\":5.0 TO 6.0", filterNegate.build())
    }

    @Test
    fun tag() {
        val filter = FilterTag("valueA")
        val filterNegate = FilterTag("valueA").not()

        assertEquals("_tags:\"valueA\"", filter.build())
        assertEquals("NOT _tags:\"valueA\"", filterNegate.build())
    }

    @Test
    fun comparison() {
        val filter = FilterComparison(attributeA, NumericOperator.Greater, 5.0)
        val filterNegate = FilterComparison(attributeA, NumericOperator.Greater, 5.0).not()

        assertEquals("\"attributeA\" > 5.0", filter.build())
        assertEquals("NOT \"attributeA\" > 5.0", filterNegate.build())
    }
}