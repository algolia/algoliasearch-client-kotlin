package serialize.search

import com.algolia.search.model.search.RemoveWordIfNoResults
import com.algolia.search.model.search.RemoveWordIfNoResults.AllOptional
import com.algolia.search.model.search.RemoveWordIfNoResults.FirstWords
import com.algolia.search.model.search.RemoveWordIfNoResults.LastWords
import com.algolia.search.model.search.RemoveWordIfNoResults.None
import com.algolia.search.model.search.RemoveWordIfNoResults.Other
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown

internal class TestRemoveWordIfNoResults : TestSerializer<RemoveWordIfNoResults>(RemoveWordIfNoResults) {

    override val items = listOf(
        None to JsonLiteral(None.raw),
        LastWords to JsonLiteral(LastWords.raw),
        FirstWords to JsonLiteral(FirstWords.raw),
        AllOptional to JsonLiteral(AllOptional.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}
