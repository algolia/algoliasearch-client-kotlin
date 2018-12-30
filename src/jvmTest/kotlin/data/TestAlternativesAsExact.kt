package data

import client.data.AlternativesAsExact.*
import client.serialize.KeyIgnorePlurals
import client.serialize.KeyMultiWordsSynonym
import client.serialize.KeySingleWordSynonym
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestAlternativesAsExact {

    @Test
    fun raw() {
        KeyIgnorePlurals shouldEqual IgnorePlurals.raw
        KeySingleWordSynonym shouldEqual SingleWordSynonym.raw
        KeyMultiWordsSynonym shouldEqual MultiWordsSynonym.raw
        unknown shouldEqual Unknown(unknown).raw
    }
}