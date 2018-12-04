import client.query.NumericOperator
import client.query.Filter
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestFilter {

    @Test
    fun boolean() {
        val filterTrue = Filter.Boolean("attributeA", true)
        val filterFalse = Filter.Boolean("attributeA", false)
        val filterTrueNegate = Filter.Boolean("attributeA", true, true)
        val filterFalseNegate = Filter.Boolean("attributeA", false, true)

        assertEquals("attributeA:true", filterTrue.raw())
        assertEquals("attributeA:false", filterFalse.raw())
        assertEquals("NOT attributeA:true", filterTrueNegate.raw())
        assertEquals("NOT attributeA:false", filterFalseNegate.raw())
    }

    @Test
    fun facet() {
        val filter = Filter.Facet("attributeA", "valueA")
        val filterNegate = Filter.Facet("attributeA", "valueA", true)

        assertEquals("attributeA:valueA", filter.raw())
        assertEquals("NOT attributeA:valueA", filterNegate.raw())
    }

    @Test
    fun range() {
        val filter = Filter.Range("attributeA", 5.0, 6.0)
        val filterNegate = Filter.Range("attributeA", 5.0, 6.0, true)

        assertEquals("attributeA:5.0 TO 6.0", filter.raw())
        assertEquals("NOT attributeA:5.0 TO 6.0", filterNegate.raw())
    }

    @Test
    fun tag() {
        val filter = Filter.Tag("attributeA")
        val filterNegate = Filter.Tag("attributeA", true)

        assertEquals("_tags:attributeA", filter.raw())
        assertEquals("NOT _tags:attributeA", filterNegate.raw())
    }

    @Test
    fun comparison() {
        val filter = Filter.Comparison("attributeA", NumericOperator.Greater, 5.0)
        val filterNegate = Filter.Comparison("attributeA", NumericOperator.Greater, 5.0, true)

        assertEquals("attributeA > 5.0", filter.raw())
        assertEquals("NOT attributeA > 5.0", filterNegate.raw())
    }
}