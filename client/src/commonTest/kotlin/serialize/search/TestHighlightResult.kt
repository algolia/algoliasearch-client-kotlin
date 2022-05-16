package serialize.search

import com.algolia.search.model.search.HighlightResult
import com.algolia.search.model.search.MatchLevel
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestHighlightResult : TestSerializer<HighlightResult>(HighlightResult.serializer()) {

    override val items = listOf(
        item to jsonObject
    )

    companion object {

        val item = HighlightResult(
            "value",
            MatchLevel.None,
            listOf("string"),
            true
        )
        val jsonObject = buildJsonObject {
            put(Key.Value, "value")
            put(Key.MatchLevel, "none")
            put(Key.MatchedWords, buildJsonArray { add("string") })
            put(Key.FullyHighlighted, true)
        }
    }
}
