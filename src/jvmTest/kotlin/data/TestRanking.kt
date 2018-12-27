package data

import attributeA
import attributeB
import client.data.Ranking
import client.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestRanking {

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
        assertEquals(KeyGeo, Ranking.Geo.raw)
        assertEquals(KeyWords, Ranking.Words.raw)
        assertEquals(KeyFilters, Ranking.Filters.raw)
        assertEquals(KeyProximity, Ranking.Proximity.raw)
        assertEquals(KeyAttribute, Ranking.Attribute.raw)
        assertEquals(KeyExact, Ranking.Exact.raw)
        assertEquals(KeyCustom, Ranking.Custom.raw)
        assertEquals("$KeyAsc($attributeA)", Ranking.Asc(attributeA).raw)
        assertEquals("$KeyDesc($attributeB)", Ranking.Desc(attributeB).raw)
        assertEquals(unknown, Ranking.Unknown(unknown).raw)
    }
}