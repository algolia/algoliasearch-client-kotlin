package data

import attributeA
import attributeB
import client.data.Ranking.*
import client.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestRanking {

    @Test
    fun raw() {
        KeyGeo shouldEqual Geo.raw
        KeyWords shouldEqual Words.raw
        KeyFilters shouldEqual Filters.raw
        KeyProximity shouldEqual Proximity.raw
        KeyAttribute shouldEqual Attribute.raw
        KeyExact shouldEqual Exact.raw
        KeyCustom shouldEqual Custom.raw
        "$KeyAsc($attributeA)" shouldEqual Asc(attributeA).raw
        "$KeyDesc($attributeB)" shouldEqual Desc(attributeB).raw
        unknown shouldEqual Unknown(unknown).raw
    }
}