package dsl.performance

import attributeA
import com.algolia.search.dsl.performance.DSLNumericAttributeFilter
import com.algolia.search.model.settings.NumericAttributeFilter
import kotlin.test.Test
import shouldEqual

internal class TestDSLNumericAttributeFilter {

    @Test
    fun default() {
        val dsl = DSLNumericAttributeFilter {
            +attributeA.raw
            +attributeA
            +attributeA.raw(true)
            +attributeA(true)
        }

        dsl shouldEqual listOf(
            NumericAttributeFilter(attributeA),
            NumericAttributeFilter(attributeA),
            NumericAttributeFilter(attributeA, true),
            NumericAttributeFilter(attributeA, true)
        )
    }
}
