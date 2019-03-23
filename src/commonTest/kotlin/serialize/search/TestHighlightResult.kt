package serialize.search

import com.algolia.search.model.search.HighlightResult
import com.algolia.search.model.search.MatchLevel
import kotlinx.serialization.json.json
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
            "value" to "value"
            "matchLevel" to "none"
            "matchedWords" to jsonArray { +"string" }
            "fullyHighlighted" to true
        }
    }
}