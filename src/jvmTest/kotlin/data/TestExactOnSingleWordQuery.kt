package data

import client.data.ExactOnSingleWordQuery.*
import client.serialize.KeyAttribute
import client.serialize.KeyNone
import client.serialize.KeyWord
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestExactOnSingleWordQuery {

    @Test
    fun raw() {
        KeyAttribute shouldEqual Attribute.raw
        KeyNone shouldEqual None.raw
        KeyWord shouldEqual Word.raw
        unknown shouldEqual Unknown(unknown).raw
    }
}