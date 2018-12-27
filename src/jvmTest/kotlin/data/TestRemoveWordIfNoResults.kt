package data

import client.data.RemoveWordIfNoResults
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
class TestRemoveWordIfNoResults {

    @Test
    fun key() {
        assertEquals("none", KeyNone)
        assertEquals("lastWords", KeyLastWords)
        assertEquals("firstWords", KeyFirstWords)
        assertEquals("allOptional", KeyAllOptional)
    }

    @Test
    fun raw() {
        assertEquals(KeyNone, RemoveWordIfNoResults.None.raw)
        assertEquals(KeyLastWords, RemoveWordIfNoResults.LastWords.raw)
        assertEquals(KeyFirstWords, RemoveWordIfNoResults.FirstWords.raw)
        assertEquals(KeyAllOptional, RemoveWordIfNoResults.AllOptional.raw)
        assertEquals(unknown, RemoveWordIfNoResults.Unknown(unknown).raw)
    }
}