package serialize.settings

import attributeA
import attributeB
import com.algolia.search.model.settings.CustomRankingCriterion
import com.algolia.search.model.settings.CustomRankingCriterion.Asc
import com.algolia.search.model.settings.CustomRankingCriterion.Desc
import com.algolia.search.model.settings.CustomRankingCriterion.Other
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestCustomRankingCriterion : TestSerializer<CustomRankingCriterion>(CustomRankingCriterion) {

    override val items = listOf(
        Asc(attributeA) to JsonPrimitive(Asc(attributeA).raw),
        Desc(attributeB) to JsonPrimitive(Desc(attributeB).raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
