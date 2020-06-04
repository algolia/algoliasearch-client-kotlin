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
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown

internal class TestRankingCriterion : TestSerializer<RankingCriterion>(RankingCriterion) {

    override val items = listOf(
        Geo to JsonLiteral(Geo.raw),
        Typo to JsonLiteral(Typo.raw),
        Words to JsonLiteral(Words.raw),
        Filters to JsonLiteral(Filters.raw),
        Proximity to JsonLiteral(Proximity.raw),
        Attribute to JsonLiteral(Attribute.raw),
        Exact to JsonLiteral(Exact.raw),
        Custom to JsonLiteral(Custom.raw),
        Asc(attributeA) to JsonLiteral(Asc(attributeA).raw),
        Desc(attributeB) to JsonLiteral(Desc(attributeB).raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}
