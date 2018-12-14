package query

import attributeA
import attributeB
import attributeC
import client.query.helper.*
import comparisonA
import comparisonB
import facetA
import facetB
import groupAndA
import groupAndB
import groupMap
import groupOrA
import groupOrB
import nameA
import nameB
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import rangeA
import rangeB
import set
import tagA
import tagB
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


@RunWith(JUnit4::class)
class TestFilterHelper {

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
        groupMap().apply {
            val filters = arrayOf(facetA, facetB, comparisonA, comparisonB, rangeA, rangeB, tagA, tagB)

            add(groupOrA, *filters)
            add(groupOrB, *filters)
            add(groupAndA, *filters)
            add(groupAndB, *filters)
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
                this
            )
        }
    }

    @Test
    fun remove() {
        groupMap().apply {
            add(groupOrA, facetA, facetB)
            add(groupOrB, facetA, facetB)
            remove(groupOrA, facetA)
            assertEquals(
                mutableMapOf(
                    Group.Key(nameA, FilterKey.OrFacet) to set(facetB),
                    Group.Key(nameB, FilterKey.OrFacet) to set(facetA, facetB)
                ),
                this
            )
            remove(groupOrA, facetB)
            assertEquals(
                mutableMapOf(
                    Group.Key(nameB, FilterKey.OrFacet) to set(facetA, facetB)
                ),
                this
            )
        }
    }

    @Test
    fun contains() {
        groupMap().apply {
            add(groupAndA, facetA)
            add(groupAndB, facetB)
            assertTrue(contains(groupAndA, facetA))
            assertFalse(contains(groupAndA, facetB))
            assertTrue(contains(facetA))
            assertTrue(contains(facetB))
        }
    }

    @Test
    fun clear() {
        groupMap().apply {
            add(groupAndA, facetA, facetB, comparisonA, comparisonB)
            clear(groupAndA, facetB.attribute)
            assertEquals(
                mutableMapOf(
                    Group.Key(nameA, FilterKey.And) to set(facetA, comparisonA)
                ),
                this
            )
            clear(groupAndA, null)
            assertTrue(isEmpty())
        }
    }

    @Test
    fun replace() {
        groupMap().apply {
            add(groupOrA, facetA)
            assertFalse(replace(groupOrB, facetA, facetB))
            assertTrue(replace(groupOrA, facetA, facetB))
            assertEquals(
                mutableMapOf(
                    Group.Key(nameA, FilterKey.OrFacet) to set(facetB)
                ),
                this
            )
        }
    }

    @Test
    fun move() {
        groupMap().apply {
            add(groupOrA, facetA)
            assertFalse(move(groupOrA, groupOrB, facetB))
            assertFalse(move(groupOrB, groupOrA, facetA))
            assertTrue(move(groupOrA, groupOrB, facetA))
            assertEquals(
                mutableMapOf(
                    Group.Key(nameB, FilterKey.OrFacet) to set(facetA)
                ),
                this
            )
        }
    }

    @Test
    fun replaceAttribute() {
        groupMap().apply {
            val original = mutableMapOf(
                Group.Key(nameA, FilterKey.And) to set(facetA, facetB, comparisonA, comparisonB)
            )

            add(groupAndA, facetA, facetB, comparisonA, comparisonB)
            replaceAttribute(groupAndA, attributeC, attributeA)
            assertEquals(this, original)
            replaceAttribute(groupAndB, attributeA, attributeB)
            assertEquals(this, original)
            replaceAttribute(groupAndA, attributeA, attributeC)
            assertEquals(
                mutableMapOf(
                    Group.Key(nameA, FilterKey.And) to set(
                        facetA.copy(attribute = attributeC),
                        facetB,
                        comparisonA.copy(attribute = attributeC),
                        comparisonB
                    )
                ),
                this
            )
        }
    }

    @Test
    fun get() {
        groupMap().apply {
            add(groupAndA, facetA, facetB)
            add(groupAndB, facetA, facetB)
            assertEquals(
                set(facetA),
                get(groupAndA, attributeA)
            )
            assertEquals(
                set(facetA, facetB),
                get(groupAndA, null)
            )
            assertEquals(
                set(facetA, facetB),
                get(null)
            )
        }
    }
}