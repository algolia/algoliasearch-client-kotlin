package data

import client.data.AroundRadius
import client.serialize.KeyAll
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestAroundRadius {

    @Test
    fun key() {
        assertEquals("all", KeyAll)
    }

    @Test
    fun raw() {
        assertEquals(KeyAll, AroundRadius.All.raw)
        assertEquals("10", AroundRadius.InMeters(10).raw)
        assertEquals(unknown, AroundRadius.Unknown(unknown).raw)
    }
}