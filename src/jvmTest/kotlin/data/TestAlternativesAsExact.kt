package data

import client.data.AlternativesAsExact
import client.serialize.KeyIgnorePlurals
import client.serialize.KeyMultiWordsSynonym
import client.serialize.KeySingleWordSynonym
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestAlternativesAsExact {

    @Test
    fun key() {
        assertEquals("ignorePlurals", KeyIgnorePlurals)
        assertEquals("singleWordSynonym", KeySingleWordSynonym)
        assertEquals("multiWordsSynonym", KeyMultiWordsSynonym)
    }

    @Test
    fun raws() {
        assertEquals(KeyIgnorePlurals, AlternativesAsExact.IgnorePlurals.raw)
        assertEquals(KeySingleWordSynonym, AlternativesAsExact.SingleWordSynonym.raw)
        assertEquals(KeyMultiWordsSynonym, AlternativesAsExact.MultiWordsSynonym.raw)
        assertEquals(unknown, AlternativesAsExact.Unknown(unknown).raw)
    }
}