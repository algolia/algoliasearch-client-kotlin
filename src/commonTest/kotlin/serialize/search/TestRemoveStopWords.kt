package serialize.search

import com.algolia.search.model.search.Language.Afrikaans
import com.algolia.search.model.search.Language.Albanian
import com.algolia.search.model.search.RemoveStopWords
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import serialize.TestSerializer

internal class TestRemoveStopWords : TestSerializer<RemoveStopWords>(RemoveStopWords) {

    override val items = listOf(
        RemoveStopWords.True to JsonPrimitive(true),
        RemoveStopWords.False to JsonPrimitive(false),
        RemoveStopWords.QueryLanguages(
            Afrikaans,
            Albanian
        ) to buildJsonArray {
            add(Afrikaans.raw)
            add(Albanian.raw)
        }
    )
}
