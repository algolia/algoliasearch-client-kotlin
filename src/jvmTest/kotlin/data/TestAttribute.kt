package data

import client.data.Attribute
import client.toAttribute
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestAttribute {

    @Test
    fun dx() {
        assertEquals(Attribute("attributeA"), "attributeA".toAttribute())
    }

    @Test
    fun raw() {
        assertEquals("raw", Attribute("raw").name)
    }
}