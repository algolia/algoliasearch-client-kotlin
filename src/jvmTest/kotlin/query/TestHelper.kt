package query

import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail


@RunWith(JUnit4::class)
class TestHelper {

    private val attributeA = Attribute("attributeA")
    private val attributeB = Attribute("attributeB")
    private val attributeC = Attribute("attributeC")
    private val filterA = FilterFacet(attributeA, "facetA")
    private val filterB = FilterFacet(attributeA, "facetB")
    private val filterC = FilterBoolean(attributeC, true)
    private val filterD = FilterTag("tag")
    private val filterE = FilterComparison(attributeB, NumericOperator.Equals, 5.0)

    private fun filters() = Filters<Filter>()

    private fun set(vararg filter: Filter) = mutableSetOf(*filter)

    @Test
    fun and() {
        filters().apply {
            and(filterA)
            assertEquals(set(filterA), ands)
            assertTrue(ors.isEmpty())
            and(filterB, filterC)
            assertEquals(set(filterA, filterB, filterC), ands)
            assertTrue(ors.isEmpty())
        }
    }

    @Test
    fun or() {
        filters().apply {
            or(filterA)
            assertEquals(set(filterA), ors)
            assertTrue(ands.isEmpty())
            or(filterB, filterC)
            assertEquals(set(filterA, filterB, filterC), ors)
            assertTrue(ands.isEmpty())
        }
    }

    @Test
    fun orShouldThrow() {
        filters().apply {
            or(filterA)
            assertEquals(set(filterA), ors)
            var thrown = false
            try {
                or(filterE.modifyAttribute(filterA.attribute))
            } catch (exception: Exception) {
                exception.printStackTrace()
                thrown = true
            }
            assertTrue(thrown)
        }
    }

    @Test
    fun orShouldNotThrow() {
        filters().apply {
            or(filterA)
            assertEquals(set(filterA), ors)
            try {
                or(filterC.modifyAttribute(filterA.attribute))
            } catch (exception: Exception) {
                fail(exception.localizedMessage)
            }
        }
    }


    @Test
    fun replace() {
        filters().apply {
            replace(filterA, filterB)
            assertTrue(ands.isEmpty())
            assertTrue(ors.isEmpty())
            and(filterA, filterA, filterC)
            or(filterA)
            replace(filterA, filterB)
            assertEquals(set(filterB, filterB, filterC), ands)
            assertEquals(set(filterB), ors)
        }
    }

    @Test
    fun remove() {
        filters().apply {
            remove(filterA, filterB)
            assertTrue(ands.isEmpty())
            assertTrue(ors.isEmpty())
            and(filterA, filterA, filterC)
            or(filterB)
            remove(filterA, filterB)
            assertEquals(set(filterC), ands)
            assertTrue(ors.isEmpty())
        }
    }

    @Test
    fun getFilters() {
        filters().apply {
            assertTrue(getFilters(attributeA).isEmpty())
            and(filterA, filterB)
            or(filterC, filterD, filterE)
            assertEquals(setOf(filterA, filterB), getFilters(attributeA))
            assertEquals(setOf(filterE), getFilters(attributeB))
            assertEquals(setOf(filterA, filterB, filterC, filterD, filterE), getFilters())
        }
    }

    @Test
    fun clear() {
        filters().apply {
            and(filterA, filterC)
            or(filterA, filterC)
            clear(attributeA)
            assertEquals(set(filterC), ands)
            assertEquals(set(filterC), ors)
            clear(null)
            assertTrue(ands.isEmpty())
            assertTrue(ors.isEmpty())
        }
    }

    @Test
    fun replaceAttribute() {
        filters().apply {
            or(filterA, filterB, filterC, filterE)
            and(filterA)
            replaceAttribute(attributeA, attributeB)
            assertEquals(
                set(
                    filterA.modifyAttribute(attributeB),
                    filterB.modifyAttribute(attributeB),
                    filterC,
                    filterE
                ),
                ors
            )
            assertEquals(set(filterA.modifyAttribute(attributeB)), ands)
            replaceAttribute(attributeB, attributeC)
            assertEquals(
                set(
                    filterA.modifyAttribute(attributeC),
                    filterB.modifyAttribute(attributeC),
                    filterC,
                    filterE.modifyAttribute(attributeC)
                ),
                ors
            )
            assertEquals(set(filterA.modifyAttribute(attributeC)), ands)
        }
    }
}