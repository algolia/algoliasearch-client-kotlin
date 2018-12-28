package data

import client.data.QueryType.*
import client.serialize.KeyPrefixAll
import client.serialize.KeyPrefixLast
import client.serialize.KeyPrefixNone
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestQueryType {

    @Test
    fun key() {
        assertEquals("prefixLast", KeyPrefixLast)
        assertEquals("prefixAll", KeyPrefixAll)
        assertEquals("prefixNone", KeyPrefixNone)
    }

    @Test
    fun raw() {
        assertEquals(KeyPrefixLast, PrefixLast.raw)
        assertEquals(KeyPrefixAll, PrefixAll.raw)
        assertEquals(KeyPrefixNone, PrefixNone.raw)
        assertEquals(unknown, Unknown(unknown).raw)
    }
}