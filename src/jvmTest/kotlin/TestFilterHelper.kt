import client.query.BooleanOperator
import client.query.Filter
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

        helper.add(facet)
        assertEquals("attributeA:valueA", helper.raw())
        helper.remove(facet)
        assertEquals("", helper.raw())
    }

    @Test
    fun twoConjunctives() {
        val helper = FilterHelper()
        val facet = Filter.Facet("attributeA", "valueA")
        val tag = Filter.Tag("attributeB")

        helper.add(facet, tag)
        assertEquals("attributeA:valueA AND _tags:attributeB", helper.raw())
        helper.remove(facet)
        assertEquals("_tags:attributeB", helper.raw())
        helper.remove(tag)
        assertEquals("", helper.raw())
    }

    @Test
    fun twoConjunctivesNegate() {
        val helper = FilterHelper()
        val boolean = Filter.Boolean("attributeA", true)
        val range = Filter.Range("attributeB", 5.0, 6.0, true)

        helper.add(boolean, range)
        assertEquals("attributeA:true AND NOT attributeB:5.0 TO 6.0", helper.raw())
        helper.remove(boolean)
        assertEquals("NOT attributeB:5.0 TO 6.0", helper.raw())
        helper.remove(range)
        assertEquals("", helper.raw())
    }

    @Test
    fun oneDisjunctiveGroup() {
        val helper = FilterHelper()
        val comparisonA = Filter.Comparison("attributeA", BooleanOperator.NotEquals, 10.0)
        val comparisonB = Filter.Comparison("attributeB", BooleanOperator.Equals, 5.0, true)

        helper.addDisjunctiveGroup(comparisonA, comparisonB)
        assertEquals("attributeA != 10.0 OR NOT attributeB = 5.0", helper.raw())
        helper.remove(comparisonA)
        assertEquals("NOT attributeB = 5.0", helper.raw())
    }

    @Test
    fun oneConjunctiveAndDisjunctive() {
        val helper = FilterHelper()
        val facet = Filter.Facet("attributeA", "valueA")
        val booleanA = Filter.Boolean("attributeB", false)
        val booleanB = Filter.Boolean("attributeC", true, true)

        helper.add(facet)
        helper.addDisjunctiveGroup(booleanA, booleanB)
        assertEquals("attributeA:valueA AND (attributeB:false OR NOT attributeC:true)", helper.raw())
        helper.remove(booleanA)
        assertEquals("attributeA:valueA AND NOT attributeC:true", helper.raw())
        helper.addDisjunctiveGroup(booleanA)
        assertEquals("attributeA:valueA AND NOT attributeC:true AND attributeB:false", helper.raw())
        helper.remove(booleanA, booleanB)
        helper.addDisjunctiveGroup(booleanA, booleanB)
        assertEquals("attributeA:valueA AND (attributeB:false OR NOT attributeC:true)", helper.raw())
    }

    @Test
    fun twoDisjunctive() {
        val helper = FilterHelper()
        val facetA = Filter.Facet("attributeA", "valueA")
        val facetB = Filter.Facet("attributeB", "valueB")
        val booleanA = Filter.Boolean("attributeC", false)
        val booleanB = Filter.Boolean("attributeD", true, true)

        helper.addDisjunctiveGroup(facetA, facetB)
        helper.addDisjunctiveGroup(booleanA, booleanB)
        assertEquals("(attributeA:valueA OR attributeB:valueB) AND (attributeC:false OR NOT attributeD:true)", helper.raw())
        helper.remove(booleanA, booleanB)
        assertEquals("attributeA:valueA OR attributeB:valueB", helper.raw())
    }

    @Test
    fun replace() {
        val helper = FilterHelper()
        val facetA = Filter.Facet("attributeA", "valueA")
        val facetB = Filter.Facet("attributeB", "valueB")
        val facetC = Filter.Facet("attributeC", "valueC")

        helper.add(facetA, facetB)
        helper.addDisjunctiveGroup(facetA, facetB)
        assertEquals("attributeA:valueA AND attributeB:valueB AND (attributeA:valueA OR attributeB:valueB)", helper.raw())
        helper.replace(facetA, facetC)
        assertEquals("attributeC:valueC AND attributeB:valueB AND (attributeC:valueC OR attributeB:valueB)", helper.raw())
    }
}