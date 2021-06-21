package serialize.settings

import attributeA
import attributeB
import com.algolia.search.model.settings.RankingCriterion
import com.algolia.search.model.settings.RankingCriterion.Asc
import com.algolia.search.model.settings.RankingCriterion.Attribute
import com.algolia.search.model.settings.RankingCriterion.Custom
import com.algolia.search.model.settings.RankingCriterion.Desc
import com.algolia.search.model.settings.RankingCriterion.Exact
import com.algolia.search.model.settings.RankingCriterion.Filters
import com.algolia.search.model.settings.RankingCriterion.Geo
import com.algolia.search.model.settings.RankingCriterion.Other
import com.algolia.search.model.settings.RankingCriterion.Proximity
import com.algolia.search.model.settings.RankingCriterion.Typo
import com.algolia.search.model.settings.RankingCriterion.Words
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestRankingCriterion : TestSerializer<RankingCriterion>(RankingCriterion) {

    override val items = listOf(
        Geo to JsonPrimitive(Geo.raw),
        Typo to JsonPrimitive(Typo.raw),
        Words to JsonPrimitive(Words.raw),
        Filters to JsonPrimitive(Filters.raw),
        Proximity to JsonPrimitive(Proximity.raw),
        Attribute to JsonPrimitive(Attribute.raw),
        Exact to JsonPrimitive(Exact.raw),
        Custom to JsonPrimitive(Custom.raw),
        Asc(attributeA) to JsonPrimitive(Asc(attributeA).raw),
        Desc(attributeB) to JsonPrimitive(Desc(attributeB).raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
