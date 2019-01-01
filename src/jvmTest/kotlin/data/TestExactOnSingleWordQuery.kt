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
        Attribute.raw shouldEqual KeyAttribute
        None.raw shouldEqual KeyNone
        Word.raw shouldEqual KeyWord
        Unknown(unknown).raw shouldEqual unknown
    }
}