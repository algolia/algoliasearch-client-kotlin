package serialize.search

import com.algolia.search.model.search.HighlightResult
import com.algolia.search.model.search.MatchLevel
import com.algolia.search.serialize.KeyFullyHighlighted
import com.algolia.search.serialize.KeyMatchLevel
import com.algolia.search.serialize.KeyMatchedWords
import com.algolia.search.serialize.KeyValue
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer

internal class TestHighlightResult : TestSerializer<HighlightResult>(HighlightResult.serializer()) {

    override val items = listOf(
        item to json
    )

    companion object {

        val item = HighlightResult(
            "value",
            MatchLevel.None,
            listOf("string"),
            true
        )
        val json = json {
            KeyValue to "value"
            KeyMatchLevel to "none"
            KeyMatchedWords to jsonArray { +"string" }
            KeyFullyHighlighted to true
        }
    }
}
