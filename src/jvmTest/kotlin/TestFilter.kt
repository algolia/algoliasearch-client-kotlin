
import client.query.helper.Attribute
import client.query.helper.Filter
import client.query.helper.NumericOperator
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestFilter {

    private val attributeA = Attribute("attributeA")

    @Test
    fun boolean() {
        val filterTrue = Filter.Boolean(attributeA, true)
        val filterFalse = Filter.Boolean(attributeA, false)
        val filterTrueNegate = Filter.Boolean(attributeA, true, true)
        val filterFalseNegate = Filter.Boolean(attributeA, false, true)

        assertEquals("attributeA:true", filterTrue.build())
        assertEquals("attributeA:false", filterFalse.build())
        assertEquals("NOT attributeA:true", filterTrueNegate.build())
        assertEquals("NOT attributeA:false", filterFalseNegate.build())
    }

    @Test
    fun facet() {
        val filter = Filter.Facet(attributeA, "valueA")
        val filterNegate = Filter.Facet(attributeA, "valueA", true)

        assertEquals("attributeA:valueA", filter.build())
        assertEquals("NOT attributeA:valueA", filterNegate.build())
    }

    @Test
    fun range() {
        val filter = Filter.Range(attributeA, 5.0, 6.0)
        val filterNegate = Filter.Range(attributeA, 5.0, 6.0, true)

        assertEquals("attributeA:5.0 TO 6.0", filter.build())
        assertEquals("NOT attributeA:5.0 TO 6.0", filterNegate.build())
    }

    @Test
    fun tag() {
        val filter = Filter.Tag("valueA")
        val filterNegate = Filter.Tag("valueA", true)

        assertEquals("_tags:valueA", filter.build())
        assertEquals("NOT _tags:valueA", filterNegate.build())
    }

    @Test
    fun comparison() {
        val filter = Filter.Comparison(attributeA, NumericOperator.Greater, 5.0)
        val filterNegate = Filter.Comparison(attributeA, NumericOperator.Greater, 5.0, true)

        assertEquals("attributeA > 5.0", filter.build())
        assertEquals("NOT attributeA > 5.0", filterNegate.build())
    }
}