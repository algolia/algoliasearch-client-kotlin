package data

import client.data.AroundRadius.*
import client.serialize.KeyAll
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestAroundRadius {

    @Test
    fun raw() {
        All.raw shouldEqual KeyAll
        InMeters(10).raw shouldEqual "10"
        Unknown(unknown).raw shouldEqual unknown
    }
}