package serialize.settings

import attributeA
import attributeB
import com.algolia.search.model.settings.RankingCriterium
import com.algolia.search.model.settings.RankingCriterium.*
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown


internal class TestRankingCriterium : TestSerializer<RankingCriterium>(RankingCriterium) {

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