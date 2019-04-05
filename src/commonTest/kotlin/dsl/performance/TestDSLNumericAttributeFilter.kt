package dsl.performance

import attributeA
import com.algolia.search.dsl.performance.DSLNumericAttributeFilter
import com.algolia.search.model.settings.NumericAttributeFilter
import shouldEqual
import kotlin.test.Test


internal class TestDSLNumericAttributeFilter {

    @Test
    fun default() {
        val dsl = DSLNumericAttributeFilter {
            +attributeA.raw
            +attributeA
            +(attributeA.raw equalOnly true)
            +(attributeA equalOnly true)
        }

        dsl shouldEqual listOf(
            NumericAttributeFilter(attributeA),
            NumericAttributeFilter(attributeA),
            NumericAttributeFilter(attributeA, true),
            NumericAttributeFilter(attributeA, true)
        )
    }
}