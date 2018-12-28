package data

import client.data.RemoveWordIfNoResults.*
import client.serialize.KeyAllOptional
import client.serialize.KeyFirstWords
import client.serialize.KeyLastWords
import client.serialize.KeyNone
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestRemoveWordIfNoResults {

    @Test
    fun raw() {
        assertEquals(KeyNone, None.raw)
        assertEquals(KeyLastWords, LastWords.raw)
        assertEquals(KeyFirstWords, FirstWords.raw)
        assertEquals(KeyAllOptional, AllOptional.raw)
        assertEquals(unknown, Unknown(unknown).raw)
    }
}