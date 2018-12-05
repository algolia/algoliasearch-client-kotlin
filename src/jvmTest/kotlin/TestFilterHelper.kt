import client.query.Filter
import client.query.NumericOperator
import client.query.helper.FilterHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestFilterHelper {

    @Test
    fun oneConjunctive() {
        val helper = FilterHelper()
        val facet = Filter.Facet("attributeA", "valueA")

        helper.addFilterAnd(facet)
        assertEquals("attributeA:valueA", helper.build())
        helper.remove(facet)
        assertEquals("", helper.build())
    }

    @Test
    fun twoConjunctives() {
        val helper = FilterHelper()
        val facet = Filter.Facet("attributeA", "valueA")
        val tag = Filter.Tag("attributeB")

        helper.addFilterAnd(facet, tag)
        assertEquals("attributeA:valueA AND _tags:attributeB", helper.build())
        helper.remove(facet)
        assertEquals("_tags:attributeB", helper.build())
        helper.remove(tag)
        assertEquals("", helper.build())
    }

    @Test
    fun twoConjunctivesNegate() {
        val helper = FilterHelper()
        val boolean = Filter.Boolean("attributeA", true)
        val range = Filter.Range("attributeB", 5.0, 6.0, true)

        helper.addFilterAnd(boolean, range)
        assertEquals("attributeA:true AND NOT attributeB:5.0 TO 6.0", helper.build())
        helper.remove(boolean)
        assertEquals("NOT attributeB:5.0 TO 6.0", helper.build())
        helper.remove(range)
        assertEquals("", helper.build())
    }

    @Test
    fun oneDisjunctiveGroup() {
        val helper = FilterHelper()
        val comparisonA = Filter.Comparison("attributeA", NumericOperator.NotEquals, 10.0)
        val comparisonB = Filter.Comparison("attributeB", NumericOperator.Equals, 5.0, true)

        helper.addFilterOr(comparisonA, comparisonB)
        assertEquals("attributeA != 10.0 OR NOT attributeB = 5.0", helper.build())
        helper.remove(comparisonA)
        assertEquals("NOT attributeB = 5.0", helper.build())
    }

    @Test
    fun oneConjunctiveAndDisjunctive() {
        val helper = FilterHelper()
        val facet = Filter.Facet("attributeA", "valueA")
        val booleanA = Filter.Boolean("attributeB", false)
        val booleanB = Filter.Boolean("attributeC", true, true)

        helper
            .addFilterAnd(facet)
            .addFilterOr(booleanA, booleanB)
        assertEquals("attributeA:valueA AND (attributeB:false OR NOT attributeC:true)", helper.build())
        helper.remove(booleanA)
        assertEquals("attributeA:valueA AND NOT attributeC:true", helper.build())
        helper
            .remove(booleanA, booleanB)
            .addFilterOr(booleanA, booleanB)
        assertEquals("attributeA:valueA AND (attributeB:false OR NOT attributeC:true)", helper.build())
    }

    @Test
    fun twoDisjunctive() {
        val helper = FilterHelper()
        val facetA = Filter.Facet("attributeA", "valueA")
        val facetB = Filter.Facet("attributeB", "valueB")
        val booleanA = Filter.Boolean("attributeC", false)
        val booleanB = Filter.Boolean("attributeD", true, true)

        helper
            .addFilterOr(facetA, facetB)
            .addFilterOr(booleanA, booleanB)
        assertEquals(
            "(attributeA:valueA OR attributeB:valueB) AND (attributeC:false OR NOT attributeD:true)",
            helper.build()
        )
        helper.remove(booleanA, booleanB)
        assertEquals("attributeA:valueA OR attributeB:valueB", helper.build())
    }

    @Test
    fun replace() {
        val helper = FilterHelper()
        val facetA = Filter.Facet("attributeA", "valueA")
        val facetB = Filter.Facet("attributeB", "valueB")
        val facetC = Filter.Facet("attributeC", "valueC")

        helper
            .addFilterAnd(facetA, facetB)
            .addFilterOr(facetA, facetB)
        assertEquals(
            "attributeA:valueA AND attributeB:valueB AND (attributeA:valueA OR attributeB:valueB)",
            helper.build()
        )
        helper.replace(facetA, facetC)
        assertEquals(
            "attributeC:valueC AND attributeB:valueB AND (attributeC:valueC OR attributeB:valueB)",
            helper.build()
        )
    }

    @Test
    fun clear() {
        val helper = FilterHelper()
        val facetA = Filter.Facet("attributeA", "valueA")
        val facetB = Filter.Facet("attributeB", "valueB")

        helper
            .addFilterAnd(facetA, facetB)
            .addFilterOr(facetA, facetB)
        assertEquals(
            "attributeA:valueA AND attributeB:valueB AND (attributeA:valueA OR attributeB:valueB)",
            helper.build()
        )
        helper.clear()
        assertEquals("", helper.build())
    }

    @Test
    fun variant() {
        val helper = FilterHelper()
        val filterA = Filter.Facet("attributeA", "valueA", variant = "variantA")
        val filterB = Filter.Boolean("attributeB", true, variant = "variantB")
        val filterC = Filter.Comparison("attributeC", NumericOperator.Greater, 10.0, variant = "variantA")

        helper.addFilterAnd(filterA, filterB, filterC)
        assertEquals("attributeA:valueA AND attributeB:true AND attributeC > 10.0", helper.build())
        helper.clear("variantA")
        assertEquals("attributeB:true", helper.build())
    }

    @Test
    fun replaceAttribute() {
        val helper = FilterHelper()
        val filterA = Filter.Facet("attributeA", "valueA", variant = "variantA")
        val filterB = Filter.Boolean("attributeA", true, variant = "variantB")
        val filterC = Filter.Comparison("attributeA", NumericOperator.Greater, 10.0, variant = "variantA")

        helper.addFilterAnd(filterA, filterB, filterC)
        assertEquals("attributeA:valueA AND attributeA:true AND attributeA > 10.0", helper.build())
        helper.replaceAttribute("attributeA", "attributeB")
        assertEquals("attributeB:valueA AND attributeB:true AND attributeB > 10.0", helper.build())
        helper.replaceAttribute("attributeB", "attributeC", "variantA")
        assertEquals("attributeC:valueA AND attributeB:true AND attributeC > 10.0", helper.build())
    }
}