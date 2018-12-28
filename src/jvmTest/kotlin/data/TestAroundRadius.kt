package data

import client.data.AroundRadius.*
import client.serialize.KeyAll
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestAroundRadius {

    @Test
    fun raw() {
        assertEquals(KeyAll, All.raw)
        assertEquals("10", InMeters(10).raw)
        assertEquals(unknown, Unknown(unknown).raw)
    }
}