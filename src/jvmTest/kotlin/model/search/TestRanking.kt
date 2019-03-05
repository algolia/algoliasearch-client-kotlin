package model.search

import attributeA
import attributeB
import com.algolia.search.model.settings.Ranking.*
import com.algolia.search.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestRanking {

    @Test
    fun raw() {
        Geo.raw shouldEqual KeyGeo
        Words.raw shouldEqual KeyWords
        Filters.raw shouldEqual KeyFilters
        Proximity.raw shouldEqual KeyProximity
        Attribute.raw shouldEqual KeyAttribute
        Exact.raw shouldEqual KeyExact
        Custom.raw shouldEqual KeyCustom
        Asc(attributeA).raw shouldEqual "$KeyAsc($attributeA)"
        Desc(attributeB).raw shouldEqual "$KeyDesc($attributeB)"
        Other(unknown).raw shouldEqual unknown
    }
}