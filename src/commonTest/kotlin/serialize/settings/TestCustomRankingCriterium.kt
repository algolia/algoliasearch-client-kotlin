package serialize.settings

import attributeA
import attributeB
import com.algolia.search.model.settings.CustomRankingCriterium
import com.algolia.search.model.settings.CustomRankingCriterium.*
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown


internal class TestCustomRankingCriterium : TestSerializer<CustomRankingCriterium>(CustomRankingCriterium) {

    override val items = listOf(
        Asc(attributeA) to JsonLiteral(Asc(attributeA).raw),
        Desc(attributeB) to JsonLiteral(Desc(attributeB).raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}