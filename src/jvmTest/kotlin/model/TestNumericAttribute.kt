package model

import attributeA
import com.algolia.search.model.enums.NumericAttributeFilter
import com.algolia.search.serialize.KeyEqualOnly
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestNumericAttribute {

    @Test
    fun raw() {
        NumericAttributeFilter(attributeA).raw shouldEqual attributeA.raw
        NumericAttributeFilter(attributeA, true).raw shouldEqual "$KeyEqualOnly($attributeA)"
    }
}