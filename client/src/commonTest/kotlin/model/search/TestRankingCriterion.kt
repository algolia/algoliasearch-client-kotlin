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
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestRankingCriterion {

    @Test
    fun raw() {
        Geo.raw shouldEqual Key.Geo
        Words.raw shouldEqual Key.Words
        Filters.raw shouldEqual Key.Filters
        Proximity.raw shouldEqual Key.Proximity
        Attribute.raw shouldEqual Key.Attribute
        Exact.raw shouldEqual Key.Exact
        Custom.raw shouldEqual Key.Custom
        Asc(attributeA).raw shouldEqual "${Key.Asc}($attributeA)"
        Desc(attributeB).raw shouldEqual "${Key.Desc}($attributeB)"
        Other(unknown).raw shouldEqual unknown
    }
}
