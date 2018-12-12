package query

import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestHelper {

    private val attributeA = Attribute("attributeA")
    private val attributeB = Attribute("attributeB")
    private val facetA = FilterFacet(attributeA, "facetA")
    private val facetB = FilterFacet(attributeB, false)
    private val comparisonA = FilterComparison(attributeA, NumericOperator.Greater, 5.0)
    private val comparisonB = FilterComparison(attributeB, NumericOperator.NotEquals, 10.0)
    private val rangeA = FilterRange(attributeA, 0.0, 5.0)
    private val rangeB = FilterRange(attributeB, 5.0, 10.0)
    private val tagA = FilterTag("tagA")
    private val tagB = FilterTag("tagB")
    private val nameA = "nameA"
    private val nameB = "nameB"
    private val groupOrA = Group.Or(nameA)
    private val groupOrB = Group.Or(nameB)
    private val groupAndA = Group.And(nameA)
    private val groupAndB = Group.And(nameB)

    private fun set(vararg filters: Filter) = mutableSetOf(*filters)

    @Test
    fun keyOr() {
        assertEquals(GroupKey(nameA, ClassKey.FilterFacet), groupOrA.key(facetA))
        assertEquals(GroupKey(nameA, ClassKey.FilterFacet), groupOrA.key(facetB))
        assertEquals(GroupKey(nameA, ClassKey.FilterNumeric), groupOrA.key(comparisonA))
        assertEquals(GroupKey(nameA, ClassKey.FilterNumeric), groupOrA.key(rangeA))
        assertEquals(GroupKey(nameA, ClassKey.FilterTag), groupOrA.key(tagA))
    }

    @Test
    fun keyAnd() {
        val key = GroupKey(nameA, ClassKey.Filter)

        assertEquals(key, groupAndA.key(facetA))
        assertEquals(key, groupAndA.key(facetB))
        assertEquals(key, groupAndA.key(comparisonA))
        assertEquals(key, groupAndA.key(rangeA))
        assertEquals(key, groupAndA.key(tagA))
    }

    @Test
    fun add() {
        val map: GroupMap = mutableMapOf()
        val filters = arrayOf(facetA, facetB, comparisonA, comparisonB, rangeA, rangeB, tagA, tagB)

        map.apply {
            add(groupOrA, *filters)
            add(groupOrB, *filters)
            add(groupAndA, *filters)
            add(groupAndB, *filters)
        }
        assertEquals(
            mutableMapOf(
                GroupKey(nameA, ClassKey.FilterFacet) to set(facetA, facetB),
                GroupKey(nameA, ClassKey.FilterNumeric) to set(comparisonA, comparisonB, rangeA, rangeB),
                GroupKey(nameA, ClassKey.FilterTag) to set(tagA, tagB),
                GroupKey(nameA, ClassKey.Filter) to set(*filters),
                GroupKey(nameB, ClassKey.FilterFacet) to set(facetA, facetB),
                GroupKey(nameB, ClassKey.FilterNumeric) to set(comparisonA, comparisonB, rangeA, rangeB),
                GroupKey(nameB, ClassKey.FilterTag) to set(tagA, tagB),
                GroupKey(nameB, ClassKey.Filter) to set(*filters)
            ),
            map
        )
    }
}