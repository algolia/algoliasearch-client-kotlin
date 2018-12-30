package data

import attributeA
import client.data.NumericAttributeFilter
import client.serialize.KeyEqualOnly
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestNumericAttribute {

    @Test
    fun raw() {
        attributeA.raw shouldEqual NumericAttributeFilter(attributeA).raw
        "$KeyEqualOnly($attributeA)" shouldEqual NumericAttributeFilter(attributeA, true).raw
    }
}