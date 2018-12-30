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
        KeyNone shouldEqual None.raw
        KeyLastWords shouldEqual LastWords.raw
        KeyFirstWords shouldEqual FirstWords.raw
        KeyAllOptional shouldEqual AllOptional.raw
        unknown shouldEqual Unknown(unknown).raw
    }
}