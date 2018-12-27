package data

import boolean
import client.data.TypoTolerance
import client.serialize.KeyMin
import client.serialize.KeyStrict
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestTypoTolerance {

    @Test
    fun key() {
        assertEquals("strict", KeyStrict)
        assertEquals("min", KeyMin)
    }

    @Test
    fun raw() {
        assertEquals("$boolean", TypoTolerance.Boolean(boolean).raw)
        assertEquals(KeyStrict, TypoTolerance.Strict.raw)
        assertEquals(KeyMin, TypoTolerance.Min.raw)
        assertEquals(unknown, TypoTolerance.Unknown(unknown).raw)
    }
}