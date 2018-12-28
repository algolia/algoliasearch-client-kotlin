package data

import client.data.MultipleQueriesStrategy.*
import client.serialize.KeyNone
import client.serialize.KeyStopIfEnoughMatches
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestMultipleQueriesStrategy {

    @Test
    fun key() {
        assertEquals("none", KeyNone)
        assertEquals("stopIfEnoughMatches", KeyStopIfEnoughMatches)
    }

    @Test
    fun raw() {
        assertEquals(KeyNone, None.raw)
        assertEquals(KeyStopIfEnoughMatches, StopIfEnoughMatches.raw)
        assertEquals(unknown, Unknown(unknown).raw)
    }
}