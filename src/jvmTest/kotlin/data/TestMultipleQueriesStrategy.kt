package data

import client.data.MultipleQueriesStrategy.*
import client.serialize.KeyNone
import client.serialize.KeyStopIfEnoughMatches
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
class TestMultipleQueriesStrategy {

    @Test
    fun raw() {
        KeyNone shouldEqual None.raw
        KeyStopIfEnoughMatches shouldEqual StopIfEnoughMatches.raw
        unknown shouldEqual Unknown(unknown).raw
    }
}