package data

import client.data.ExactOnSingleWordQuery
import client.serialize.KeyAttribute
import client.serialize.KeyNone
import client.serialize.KeyWord
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestExactOnSingleWordQuery {

    @Test
    fun key() {
        assertEquals("attribute", KeyAttribute)
        assertEquals("none", KeyNone)
        assertEquals("word", KeyWord)
    }

    @Test
    fun raw() {
        assertEquals(KeyAttribute, ExactOnSingleWordQuery.Attribute.raw)
        assertEquals(KeyNone, ExactOnSingleWordQuery.None.raw)
        assertEquals(KeyWord, ExactOnSingleWordQuery.Word.raw)
        assertEquals(unknown, ExactOnSingleWordQuery.Unknown(unknown).raw)
    }
}