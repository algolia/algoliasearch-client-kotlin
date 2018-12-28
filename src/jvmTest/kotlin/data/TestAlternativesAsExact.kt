package data

import client.data.AlternativesAsExact.*
import client.serialize.KeyIgnorePlurals
import client.serialize.KeyMultiWordsSynonym
import client.serialize.KeySingleWordSynonym
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestAlternativesAsExact {

    @Test
    fun raw() {
        assertEquals(KeyIgnorePlurals, IgnorePlurals.raw)
        assertEquals(KeySingleWordSynonym, SingleWordSynonym.raw)
        assertEquals(KeyMultiWordsSynonym, MultiWordsSynonym.raw)
        assertEquals(unknown, Unknown(unknown).raw)
    }
}