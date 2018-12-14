package query

import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


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
    private val groupOrA = GroupOr(nameA)
    private val groupOrB = GroupOr(nameB)
    private val groupAndA = GroupAnd(nameA)
    private val groupAndB = GroupAnd(nameB)

    private fun groupMap(): GroupMap<Filter> = mutableMapOf()
    private fun set(vararg filters: Filter) = mutableSetOf(*filters)

    @Test
    fun keyOr() {
        assertEquals(Group.Key(nameA, FilterKey.OrFacet), groupOrA.key(facetA))
        assertEquals(Group.Key(nameA, FilterKey.OrFacet), groupOrA.key(facetB))
        assertEquals(Group.Key(nameA, FilterKey.OrNumeric), groupOrA.key(comparisonA))
        assertEquals(Group.Key(nameA, FilterKey.OrNumeric), groupOrA.key(rangeA))
        assertEquals(Group.Key(nameA, FilterKey.OrTag), groupOrA.key(tagA))
    }

    @Test
    fun keyAnd() {
        val key = Group.Key(nameA, FilterKey.And)

        assertEquals(key, groupAndA.key(facetA))
        assertEquals(key, groupAndA.key(facetB))
        assertEquals(key, groupAndA.key(comparisonA))
        assertEquals(key, groupAndA.key(rangeA))
        assertEquals(key, groupAndA.key(tagA))
    }

    @Test
    fun add() {
        val map = groupMap()
        val filters = arrayOf(facetA, facetB, comparisonA, comparisonB, rangeA, rangeB, tagA, tagB)

        map.apply {
            add(groupOrA, *filters)
            add(groupOrB, *filters)
            add(groupAndA, *filters)
            add(groupAndB, *filters)
        }
        assertEquals(
            mutableMapOf(
                Group.Key(nameA, FilterKey.OrFacet) to set(facetA, facetB),
                Group.Key(nameA, FilterKey.OrNumeric) to set(comparisonA, comparisonB, rangeA, rangeB),
                Group.Key(nameA, FilterKey.OrTag) to set(tagA, tagB),
                Group.Key(nameA, FilterKey.And) to set(*filters),
                Group.Key(nameB, FilterKey.OrFacet) to set(facetA, facetB),
                Group.Key(nameB, FilterKey.OrNumeric) to set(comparisonA, comparisonB, rangeA, rangeB),
                Group.Key(nameB, FilterKey.OrTag) to set(tagA, tagB),
                Group.Key(nameB, FilterKey.And) to set(*filters)
            ),
            map
        )
    }

    @Test
    fun remove() {
        val map = groupMap()

        map.apply {
            add(groupOrA, facetA, facetB)
            add(groupOrB, facetA, facetB)
            remove(groupOrA, facetA)
        }
        assertEquals(
            mutableMapOf(
                Group.Key(nameA, FilterKey.OrFacet) to set(facetB),
                Group.Key(nameB, FilterKey.OrFacet) to set(facetA, facetB)
            ),
            map
        )

        map.remove(groupOrA, facetB)

        assertEquals(
            mutableMapOf(
                Group.Key(nameB, FilterKey.OrFacet) to set(facetA, facetB)
            ),
            map
        )
    }

    @Test
    fun contains() {
        val map = groupMap()

        map.apply {
            add(groupAndA, facetA)
            add(groupAndB, facetB)
        }

        assertTrue(map.contains(groupAndA, facetA))
        assertFalse(map.contains(groupAndA, facetB))
        assertTrue(map.contains(facetA))
        assertTrue(map.contains(facetB))
    }

    @Test
    fun clear() {
        val map = groupMap()

        map.add(groupAndA, facetA, facetB, comparisonA, comparisonB)
        map.clear(groupAndA, facetB.attribute)
        assertEquals(
            mutableMapOf(
                Group.Key(nameA, FilterKey.And) to set(facetA, comparisonA)
            ),
            map
        )
        map.clear(groupAndA, null)
        assertTrue(map.isEmpty())
    }

    @Test
    fun replace() {
        val map = groupMap()

        map.add(groupOrA, facetA)
        assertFalse(map.replace(groupOrB, facetA, facetB))
        assertTrue(map.replace(groupOrA, facetA, facetB))
        assertEquals(
            mutableMapOf(
                Group.Key(nameA, FilterKey.OrFacet) to set(facetB)
            ),
            map
        )
    }

    @Test
    fun move() {
        val map = groupMap()

        map.add(groupOrA, facetA)
        assertFalse(map.move(groupOrA, groupOrB, facetB))
        assertFalse(map.move(groupOrB, groupOrA, facetA))
        assertTrue(map.move(groupOrA, groupOrB, facetA))
        assertEquals(
            mutableMapOf(
                Group.Key(nameB, FilterKey.OrFacet) to set(facetA)
            ),
            map
        )
    }
}