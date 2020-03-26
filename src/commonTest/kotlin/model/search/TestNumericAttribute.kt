package model.search

import attributeA
import com.algolia.search.model.settings.NumericAttributeFilter
import com.algolia.search.serialize.KeyEqualOnly
import kotlin.test.Test
import shouldEqual

internal class TestNumericAttribute {

    @Test
    fun raw() {
        NumericAttributeFilter(attributeA).raw shouldEqual attributeA.raw
        NumericAttributeFilter(attributeA, true).raw shouldEqual "$KeyEqualOnly($attributeA)"
    }
}
