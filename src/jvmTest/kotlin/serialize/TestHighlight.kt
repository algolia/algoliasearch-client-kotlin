package serialize

import com.algolia.search.saas.data.search.Highlight
import com.algolia.search.saas.data.search.MatchLevel
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestHighlight : TestSerializer<Highlight>(Highlight.serializer()) {

    override val items = listOf(
        highlightResult to json
    )

    companion object {

        val highlightResult = Highlight(
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