package query

import client.query.helper.OptionalFilterBuilder
import facetA
import facetB
import groupAndA
import groupAndB
import groupOrA
import groupOrB
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


@RunWith(JUnit4::class)
class TestOptionalFilterBuilder {

    @Test
    fun isEmpty() {
        OptionalFilterBuilder {
            assertTrue(isEmpty())
            groupAndA += facetA
            assertFalse(isEmpty())
        }
    }

    @Test
    fun clear() {
        OptionalFilterBuilder {
            groupAndA += facetA
            groupAndB += facetB
            clear()
            assertTrue(isEmpty())
        }
    }

    @Test
    fun build() {
        OptionalFilterBuilder {
            groupAndA += facetA
            groupAndA += facetB
            groupOrA += facetA
            groupOrA += facetB
            groupOrB += facetA

            assertEquals(
                listOf(
                    listOf(facetA.expression),
                    listOf(facetB.expression),
                    listOf(facetA.expression, facetB.expression),
                    listOf(facetA.expression)
                ),
                build()
            )
        }
    }
}