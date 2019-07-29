package serialize.search

import com.algolia.search.model.search.Language.Afrikaans
import com.algolia.search.model.search.Language.Albanian
import com.algolia.search.model.search.RemoveStopWords
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer


internal class TestRemoveStopWords : TestSerializer<RemoveStopWords>(RemoveStopWords) {

    override val items = listOf(
        RemoveStopWords.True to JsonLiteral(true),
        RemoveStopWords.False to JsonLiteral(false),
        RemoveStopWords.QueryLanguages(
            Afrikaans,
            Albanian
        ) to jsonArray {
            +Afrikaans.raw
            +Albanian.raw
        }
    )
}