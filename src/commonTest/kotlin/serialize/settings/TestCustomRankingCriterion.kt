package serialize.settings

import attributeA
import attributeB
import com.algolia.search.model.settings.CustomRankingCriterion
import com.algolia.search.model.settings.CustomRankingCriterion.Asc
import com.algolia.search.model.settings.CustomRankingCriterion.Desc
import com.algolia.search.model.settings.CustomRankingCriterion.Other
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown

internal class TestCustomRankingCriterion : TestSerializer<CustomRankingCriterion>(CustomRankingCriterion) {

    override val items = listOf(
        Asc(attributeA) to JsonLiteral(Asc(attributeA).raw),
        Desc(attributeB) to JsonLiteral(Desc(attributeB).raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}
