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
        Boolean(boolean).raw shouldEqual "$boolean"
        Strict.raw shouldEqual KeyStrict
        Min.raw shouldEqual KeyMin
        Unknown(unknown).raw shouldEqual unknown
    }
}