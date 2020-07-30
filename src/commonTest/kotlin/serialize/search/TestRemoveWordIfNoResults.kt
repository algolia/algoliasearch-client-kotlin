package serialize.search

import com.algolia.search.model.search.RemoveWordIfNoResults
import com.algolia.search.model.search.RemoveWordIfNoResults.AllOptional
import com.algolia.search.model.search.RemoveWordIfNoResults.FirstWords
import com.algolia.search.model.search.RemoveWordIfNoResults.LastWords
import com.algolia.search.model.search.RemoveWordIfNoResults.None
import com.algolia.search.model.search.RemoveWordIfNoResults.Other
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestRemoveWordIfNoResults : TestSerializer<RemoveWordIfNoResults>(RemoveWordIfNoResults) {

    override val items = listOf(
        None to JsonPrimitive(None.raw),
        LastWords to JsonPrimitive(LastWords.raw),
        FirstWords to JsonPrimitive(FirstWords.raw),
        AllOptional to JsonPrimitive(AllOptional.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
