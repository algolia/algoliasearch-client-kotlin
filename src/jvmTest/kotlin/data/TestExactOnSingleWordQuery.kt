package data

import client.data.ExactOnSingleWordQuery.*
import client.serialize.KeyAttribute
import client.serialize.KeyNone
import client.serialize.KeyWord
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestExactOnSingleWordQuery {

    @Test
    fun raw() {
        assertEquals(KeyAttribute, Attribute.raw)
        assertEquals(KeyNone, None.raw)
        assertEquals(KeyWord, Word.raw)
        assertEquals(unknown, Unknown(unknown).raw)
    }
}