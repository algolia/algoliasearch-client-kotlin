package query

import client.query.helper.OptionalFilterBuilder
import facetA
import facetB
import groupAndA
import groupAndB
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
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
}