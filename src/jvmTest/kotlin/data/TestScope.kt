package data

import client.data.Scope.*
import client.serialize.KeyRules
import client.serialize.KeySettings
import client.serialize.KeySynonyms
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestScope {

    @Test
    fun raw() {
        KeySettings shouldEqual Settings.raw
        KeyRules shouldEqual Rules.raw
        KeySynonyms shouldEqual Synonyms.raw
    }
}