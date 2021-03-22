package serialize.search

import com.algolia.search.model.search.ExplainModule
import com.algolia.search.model.search.ExplainModule.MatchAlternatives
import com.algolia.search.model.search.ExplainModule.Other
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestExplainModule : TestSerializer<ExplainModule>(ExplainModule) {

    override val items = listOf(
        MatchAlternatives to JsonPrimitive(MatchAlternatives.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
