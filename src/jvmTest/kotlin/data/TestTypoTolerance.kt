package data

import boolean
import client.data.TypoTolerance.*
import client.data.TypoTolerance.Boolean
import client.serialize.KeyMin
import client.serialize.KeyStrict
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestTypoTolerance {

    @Test
    fun key() {
        assertEquals("strict", KeyStrict)
        assertEquals("min", KeyMin)
    }

    @Test
    fun raw() {
        assertEquals("$boolean", Boolean(boolean).raw)
        assertEquals(KeyStrict, Strict.raw)
        assertEquals(KeyMin, Min.raw)
        assertEquals(unknown, Unknown(unknown).raw)
    }
}