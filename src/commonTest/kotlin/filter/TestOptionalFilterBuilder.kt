package filter

import com.algolia.search.filter.OptionalFilterBuilder
import facetA
import facetB
import groupAndA
import groupAndB
import groupOrA
import groupOrB
import shouldBeFalse
import shouldBeTrue
import shouldEqual
import kotlin.test.Test


internal class TestOptionalFilterBuilder {

    @Test
    fun isEmpty() {
        OptionalFilterBuilder().apply {
            isEmpty().shouldBeTrue()
            groupAndA += facetA
            isEmpty().shouldBeFalse()
        }
    }

    @Test
    fun clear() {
        OptionalFilterBuilder().apply {
            groupAndA += facetA
            groupAndB += facetB
            clear()
            isEmpty().shouldBeTrue()
        }
    }

    @Test
    fun build() {
        OptionalFilterBuilder().apply {
            groupAndA += facetA
            groupAndA += facetB
            groupOrA += facetA
            groupOrA += facetB
            groupOrB += facetA

            build() shouldEqual listOf(
                listOf(facetA.expression),
                listOf(facetB.expression),
                listOf(facetA.expression, facetB.expression),
                listOf(facetA.expression)
            )
        }
    }
}