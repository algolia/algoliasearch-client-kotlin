package data

import attributeA
import attributeB
import client.data.CustomRanking.Asc
import client.data.CustomRanking.Desc
import client.serialize.KeyAsc
import client.serialize.KeyDesc
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestCustomRanking {

    @Test
    fun key() {
        assertEquals("asc", KeyAsc)
        assertEquals("desc", KeyDesc)
    }

    @Test
    fun raw() {
        assertEquals("$KeyAsc($attributeA)", Asc(attributeA).raw)
        assertEquals("$KeyDesc($attributeB)", Desc(attributeB).raw)
    }
}