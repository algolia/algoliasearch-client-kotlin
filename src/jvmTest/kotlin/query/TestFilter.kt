package query
import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestFilter {

    private val attributeA = Attribute("attributeA")

    @Test
    fun boolean() {
        val filterTrue = FilterFacet(attributeA, true)
        val filterFalse = FilterFacet(attributeA, false)
        val filterTrueNegate = FilterFacet(attributeA, true).not()
        val filterFalseNegate = FilterFacet(attributeA, false).not()

        assertEquals("attributeA:true", filterTrue.build())
        assertEquals("attributeA:false", filterFalse.build())
        assertEquals("NOT attributeA:true", filterTrueNegate.build())
        assertEquals("NOT attributeA:false", filterFalseNegate.build())
    }

    @Test
    fun facet() {
        val filter = FilterFacet(attributeA, "valueA")
        val filterNegate = FilterFacet(attributeA, "valueA").not()

        assertEquals("attributeA:valueA", filter.build())
        assertEquals("NOT attributeA:valueA", filterNegate.build())
    }

    @Test
    fun range() {
        val filter = FilterRange(attributeA, 5.0, 6.0)
        val filterNegate = FilterRange(attributeA, 5.0, 6.0).not()

        assertEquals("attributeA:5.0 TO 6.0", filter.build())
        assertEquals("NOT attributeA:5.0 TO 6.0", filterNegate.build())
    }

    @Test
    fun tag() {
        val filter = FilterTag("valueA")
        val filterNegate = FilterTag("valueA").not()

        assertEquals("_tags:valueA", filter.build())
        assertEquals("NOT _tags:valueA", filterNegate.build())
    }

    @Test
    fun comparison() {
        val filter = FilterComparison(attributeA, NumericOperator.Greater, 5.0)
        val filterNegate = FilterComparison(attributeA, NumericOperator.Greater, 5.0).not()

        assertEquals("attributeA > 5.0", filter.build())
        assertEquals("NOT attributeA > 5.0", filterNegate.build())
    }
}