package data

import client.data.RemoveWordIfNoResults.*
import client.serialize.KeyAllOptional
import client.serialize.KeyFirstWords
import client.serialize.KeyLastWords
import client.serialize.KeyNone
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestRemoveWordIfNoResults {

    @Test
    fun raw() {
        None.raw shouldEqual KeyNone
        LastWords.raw shouldEqual KeyLastWords
        FirstWords.raw shouldEqual KeyFirstWords
        AllOptional.raw shouldEqual KeyAllOptional
        Unknown(unknown).raw shouldEqual unknown
    }
}