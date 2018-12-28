package data

import attributeA
import client.data.NumericAttributeFilter
import client.serialize.KeyEqualOnly
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestNumericAttribute {

    @Test
    fun key() {
        assertEquals("equalOnly", KeyEqualOnly)
    }

    @Test
    fun raw() {
        assertEquals(attributeA.name, NumericAttributeFilter(attributeA).raw)
        assertEquals("$KeyEqualOnly($attributeA)", NumericAttributeFilter(attributeA, true).raw)
    }
}