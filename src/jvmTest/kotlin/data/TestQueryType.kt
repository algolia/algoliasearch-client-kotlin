package data

import client.data.QueryType.*
import client.serialize.KeyPrefixAll
import client.serialize.KeyPrefixLast
import client.serialize.KeyPrefixNone
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestQueryType {

    @Test
    fun raw() {
        PrefixLast.raw shouldEqual KeyPrefixLast
        PrefixAll.raw shouldEqual KeyPrefixAll
        PrefixNone.raw shouldEqual KeyPrefixNone
        Unknown(unknown).raw shouldEqual unknown
    }
}