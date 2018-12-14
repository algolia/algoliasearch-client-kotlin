package query

import client.query.helper.FilterBuilder
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
class TestFilterBuilder {

    @Test
    fun isEmpty() {
        FilterBuilder {
            assertTrue(isEmpty())
            groupAndA += facetA
            assertFalse(isEmpty())
        }
    }

    @Test
    fun clear() {
        FilterBuilder {
            groupAndA += facetA
            groupAndB += facetB
            clear()
            assertTrue(isEmpty())
        }
    }
}