package data

import client.data.Attribute
import client.toAttribute
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestAttribute {

    @Test
    fun dx() {
        Attribute("attributeA") shouldEqual "attributeA".toAttribute()
    }

    @Test
    fun raw() {
        "raw" shouldEqual Attribute("raw").raw
    }
}