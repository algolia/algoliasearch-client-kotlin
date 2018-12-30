package data

import boolean
import client.data.TypoTolerance.*
import client.data.TypoTolerance.Boolean
import client.serialize.KeyMin
import client.serialize.KeyStrict
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestTypoTolerance {

    @Test
    fun raw() {
        "$boolean" shouldEqual Boolean(boolean).raw
        KeyStrict shouldEqual Strict.raw
        KeyMin shouldEqual Min.raw
        unknown shouldEqual Unknown(unknown).raw
    }
}