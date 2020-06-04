package model.search

import attributeA
import attributeB
import com.algolia.search.model.settings.RankingCriterion.Asc
import com.algolia.search.model.settings.RankingCriterion.Attribute
import com.algolia.search.model.settings.RankingCriterion.Custom
import com.algolia.search.model.settings.RankingCriterion.Desc
import com.algolia.search.model.settings.RankingCriterion.Exact
import com.algolia.search.model.settings.RankingCriterion.Filters
import com.algolia.search.model.settings.RankingCriterion.Geo
import com.algolia.search.model.settings.RankingCriterion.Other
import com.algolia.search.model.settings.RankingCriterion.Proximity
import com.algolia.search.model.settings.RankingCriterion.Words
import com.algolia.search.serialize.KeyAsc
import com.algolia.search.serialize.KeyAttribute
import com.algolia.search.serialize.KeyCustom
import com.algolia.search.serialize.KeyDesc
import com.algolia.search.serialize.KeyExact
import com.algolia.search.serialize.KeyFilters
import com.algolia.search.serialize.KeyGeo
import com.algolia.search.serialize.KeyProximity
import com.algolia.search.serialize.KeyWords
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestRankingCriterion {

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
