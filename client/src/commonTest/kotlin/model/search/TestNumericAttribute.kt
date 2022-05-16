package model.search

import attributeA
import com.algolia.search.model.settings.NumericAttributeFilter
import com.algolia.search.serialize.internal.Key
import shouldEqual
import kotlin.test.Test

internal class TestNumericAttribute {

    @Test
    fun raw() {
        NumericAttributeFilter(attributeA).raw shouldEqual attributeA.raw
        NumericAttributeFilter(attributeA, true).raw shouldEqual "${Key.EqualOnly}($attributeA)"
    }
}
