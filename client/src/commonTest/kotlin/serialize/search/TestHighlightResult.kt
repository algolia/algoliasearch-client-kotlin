package serialize.search

import com.algolia.search.model.search.HighlightResult
import com.algolia.search.model.search.MatchLevel
import com.algolia.search.serialize.internal.KeyFullyHighlighted
import com.algolia.search.serialize.internal.KeyMatchLevel
import com.algolia.search.serialize.internal.KeyMatchedWords
import com.algolia.search.serialize.internal.KeyValue
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
            put(KeyValue, "value")
            put(KeyMatchLevel, "none")
            put(KeyMatchedWords, buildJsonArray { add("string") })
            put(KeyFullyHighlighted, true)
        }
    }
}
