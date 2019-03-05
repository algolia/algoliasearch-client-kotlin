package filter

import com.algolia.search.filter.OptionalFilterBuilder
import facetA
import facetB
import groupAndA
import groupAndB
import groupOrA
import groupOrB
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeFalse
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
internal class TestOptionalFilterBuilder {

    @Test
    fun isEmpty() {
        OptionalFilterBuilder {
            isEmpty().shouldBeTrue()
            groupAndA += facetA
            isEmpty().shouldBeFalse()
        }
    }

    @Test
    fun clear() {
        OptionalFilterBuilder {
            groupAndA += facetA
            groupAndB += facetB
            clear()
            isEmpty().shouldBeTrue()
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

            build() shouldEqual listOf(
                listOf(facetA.expression),
                listOf(facetB.expression),
                listOf(facetA.expression, facetB.expression),
                listOf(facetA.expression)
            )
        }
    }
}