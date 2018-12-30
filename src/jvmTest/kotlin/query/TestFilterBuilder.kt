package query

import client.query.helper.FilterBuilder
import facetA
import facetB
import groupAndA
import groupAndB
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeFalse
import shouldBeTrue


@RunWith(JUnit4::class)
class TestFilterBuilder {

    @Test
    fun isEmpty() {
        FilterBuilder {
            isEmpty().shouldBeTrue()
            groupAndA += facetA
            isEmpty().shouldBeFalse()
        }
    }

    @Test
    fun clear() {
        FilterBuilder {
            groupAndA += facetA
            groupAndB += facetB
            clear()
            isEmpty().shouldBeTrue()
        }
    }
}