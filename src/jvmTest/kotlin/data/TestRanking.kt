package data

import attributeA
import attributeB
import client.data.Ranking.*
import client.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestRanking {

    @Test
    fun key() {
        assertEquals("typo", KeyTypo)
        assertEquals("geo", KeyGeo)
        assertEquals("words", KeyWords)
        assertEquals("filters", KeyFilters)
        assertEquals("proximity", KeyProximity)
        assertEquals("attribute", KeyAttribute)
        assertEquals("exact", KeyExact)
        assertEquals("custom", KeyCustom)
        assertEquals("asc", KeyAsc)
        assertEquals("desc", KeyDesc)
    }

    @Test
    fun raw() {
        assertEquals(KeyGeo, Geo.raw)
        assertEquals(KeyWords, Words.raw)
        assertEquals(KeyFilters, Filters.raw)
        assertEquals(KeyProximity, Proximity.raw)
        assertEquals(KeyAttribute, Attribute.raw)
        assertEquals(KeyExact, Exact.raw)
        assertEquals(KeyCustom, Custom.raw)
        assertEquals("$KeyAsc($attributeA)", Asc(attributeA).raw)
        assertEquals("$KeyDesc($attributeB)", Desc(attributeB).raw)
        assertEquals(unknown, Unknown(unknown).raw)
    }
}