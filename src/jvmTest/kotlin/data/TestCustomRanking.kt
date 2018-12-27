package data

import attributeA
import attributeB
import client.data.CustomRanking
import client.serialize.KeyAsc
import client.serialize.KeyDesc
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestCustomRanking {

    @Test
    fun key() {
        assertEquals("asc", KeyAsc)
        assertEquals("desc", KeyDesc)
    }

    @Test
    fun raw() {
        assertEquals("$KeyAsc($attributeA)", CustomRanking.Asc(attributeA).raw)
        assertEquals("$KeyDesc($attributeB)", CustomRanking.Desc(attributeB).raw)
    }
}