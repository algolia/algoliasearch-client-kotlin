package model.search

import attributeA
import attributeB
import com.algolia.search.model.settings.RankingCriteria.*
import com.algolia.search.serialize.*
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestRankingCriteria {

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