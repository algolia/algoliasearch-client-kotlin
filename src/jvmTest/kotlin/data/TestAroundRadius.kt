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
        KeyAll shouldEqual All.raw
        "10" shouldEqual InMeters(10).raw
        unknown shouldEqual Unknown(unknown).raw
    }
}