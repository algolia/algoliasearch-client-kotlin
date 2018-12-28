package data

import attributeA
import attributeAll
import attributeB
import client.data.Snippet
import client.serialize.KeyStar
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestSnippet {

    @Test
    fun key() {
        assertEquals("*", KeyStar)
    }

    @Test
    fun raw() {
        assertEquals(attributeA.name, Snippet(attributeA).raw)
        assertEquals("*", Snippet(attributeAll).raw)
        assertEquals("*:20", Snippet(attributeAll, 20).raw)
        assertEquals("$attributeB:10", Snippet(attributeB, 10).raw)
    }
}