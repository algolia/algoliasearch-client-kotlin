package serialize.search

import com.algolia.search.model.search.HighlightResult
import com.algolia.search.model.search.MatchLevel
import com.algolia.search.serialize.KeyFullyHighlighted
import com.algolia.search.serialize.KeyMatchLevel
import com.algolia.search.serialize.KeyMatchedWords
import com.algolia.search.serialize.KeyValue
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
