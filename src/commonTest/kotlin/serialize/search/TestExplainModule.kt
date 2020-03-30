package serialize.search

import com.algolia.search.model.search.ExplainModule
import com.algolia.search.model.search.ExplainModule.MatchAlternatives
import com.algolia.search.model.search.ExplainModule.Other
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown

internal class TestExplainModule : TestSerializer<ExplainModule>(ExplainModule) {

    override val items = listOf(
        MatchAlternatives to JsonLiteral(MatchAlternatives.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}
