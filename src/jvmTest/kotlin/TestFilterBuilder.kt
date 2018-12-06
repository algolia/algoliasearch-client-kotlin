import client.query.Query
import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestFilterBuilder {

    private val attributeA = Attribute("attributeA")
    private val attributeB = Attribute("attributeB")
    private val attributeC = Attribute("attributeC")
    private val attributeD = Attribute("attributeD")
    private val groupA = Group("groupA")
    private val groupB = Group("groupB")

    @Test
    fun assign() {
        val query = Query()

        query.filterBuilder
            .and(FilterFacet(attributeA, "valueA"))

        assertEquals("attributeA:valueA", query.filterBuilder.build())
    }

    @Test
    fun oneConjunctive() {
        val helper = FilterBuilder()
        val facet = FilterFacet(attributeA, "valueA")

        helper.and(facet)
        assertEquals("attributeA:valueA", helper.build())
        helper.remove(facet)
        assertEquals("", helper.build())
    }

    @Test
    fun twoConjunctives() {
        val helper = FilterBuilder()
        val facet = FilterFacet(attributeA, "valueA")
        val tag = FilterTag("valueB")

        helper.and(facet, tag)
        assertEquals("attributeA:valueA AND _tags:valueB", helper.build())
        helper.remove(facet)
        assertEquals("_tags:valueB", helper.build())
        helper.remove(tag)
        assertEquals("", helper.build())
    }

    @Test
    fun twoConjunctivesNegate() {
        val helper = FilterBuilder()
        val boolean = FilterBoolean(attributeA, true)
        val range = FilterRange(attributeB, 5.0, 6.0, true)

        helper.and(boolean, range)
        assertEquals("attributeA:true AND NOT attributeB:5.0 TO 6.0", helper.build())
        helper.remove(boolean)
        assertEquals("NOT attributeB:5.0 TO 6.0", helper.build())
        helper.remove(range)
        assertEquals("", helper.build())
    }

    @Test
    fun oneDisjunctiveGroup() {
        val helper = FilterBuilder()
        val comparisonA = FilterComparison(attributeA, NumericOperator.NotEquals, 10.0)
        val comparisonB = FilterComparison(attributeB, NumericOperator.Equals, 5.0, true)

        helper.or(comparisonA, comparisonB)
        assertEquals("attributeA != 10.0 OR NOT attributeB = 5.0", helper.build())
        helper.remove(comparisonA)
        assertEquals("NOT attributeB = 5.0", helper.build())
    }

    @Test
    fun oneConjunctiveAndDisjunctive() {
        val helper = FilterBuilder()
        val facet = FilterFacet(attributeA, "valueA")
        val booleanA = FilterBoolean(attributeB, false)
        val booleanB = FilterBoolean(attributeC, true, true)

        helper
            .and(facet)
            .or(booleanA, booleanB)
        assertEquals("attributeA:valueA AND (attributeB:false OR NOT attributeC:true)", helper.build())
        helper.remove(booleanA)
        assertEquals("attributeA:valueA AND NOT attributeC:true", helper.build())
        helper
            .remove(booleanA, booleanB)
            .or(booleanA, booleanB)
        assertEquals("attributeA:valueA AND (attributeB:false OR NOT attributeC:true)", helper.build())
    }

    @Test
    fun twoDisjunctive() {
        val helper = FilterBuilder()
        val facetA = FilterFacet(attributeA, "valueA")
        val facetB = FilterFacet(attributeB, "valueB")
        val booleanA = FilterBoolean(attributeC, false)
        val booleanB = FilterBoolean(attributeD, true, true)

        helper
            .or(facetA, facetB)
            .or(booleanA, booleanB)
        assertEquals(
            "(attributeA:valueA OR attributeB:valueB) AND (attributeC:false OR NOT attributeD:true)",
            helper.build()
        )
        helper.remove(booleanA, booleanB)
        assertEquals("attributeA:valueA OR attributeB:valueB", helper.build())
    }

    @Test
    fun replace() {
        val helper = FilterBuilder()
        val facetA = FilterFacet(attributeA, "valueA")
        val facetB = FilterFacet(attributeB, "valueB")
        val facetC = FilterFacet(attributeC, "valueC")

        helper
            .and(facetA, facetB)
            .or(facetA, facetB)
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
        val helper = FilterBuilder()
        val facetA = FilterFacet(attributeA, "valueA")
        val facetB = FilterFacet(attributeB, "valueB")

        helper
            .and(facetA, facetB)
            .or(facetA, facetB)
        assertEquals(
            "attributeA:valueA AND attributeB:valueB AND (attributeA:valueA OR attributeB:valueB)",
            helper.build()
        )
        helper.clear()
        assertEquals("", helper.build())
    }

    @Test
    fun group() {
        val helper = FilterBuilder()
        val filterA = FilterFacet(attributeA, "valueA", group = groupA)
        val filterB = FilterBoolean(attributeB, true, group = groupB)
        val filterC = FilterComparison(attributeC, NumericOperator.Greater, 10.0, group = groupA)

        helper.and(filterA, filterB, filterC)
        assertEquals(listOf(filterA, filterC), helper.getFilters(groupA))
        assertEquals("attributeA:valueA AND attributeB:true AND attributeC > 10.0", helper.build())
        helper.clear(groupA)
        assertEquals("attributeB:true", helper.build())
    }

    @Test
    fun replaceAttribute() {
        val helper = FilterBuilder()
        val filterA = FilterFacet(attributeA, "valueA", group = groupA)
        val filterB = FilterBoolean(attributeA, true, group = groupB)
        val filterC = FilterComparison(attributeA, NumericOperator.Greater, 10.0, group = groupA)

        helper.and(filterA, filterB, filterC)
        assertEquals("attributeA:valueA AND attributeA:true AND attributeA > 10.0", helper.build())
        helper.replaceAttribute(attributeA, attributeB)
        assertEquals("attributeB:valueA AND attributeB:true AND attributeB > 10.0", helper.build())
        helper.replaceAttribute(attributeB, attributeC, groupA)
        assertEquals("attributeC:valueA AND attributeB:true AND attributeC > 10.0", helper.build())
    }
}