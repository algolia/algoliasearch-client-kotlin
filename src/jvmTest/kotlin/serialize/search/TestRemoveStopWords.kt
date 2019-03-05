package serialize.search

import boolean
import com.algolia.search.model.search.QueryLanguage.Afrikaans
import com.algolia.search.model.search.QueryLanguage.Albanian
import com.algolia.search.model.search.RemoveStopWords
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer


@RunWith(JUnit4::class)
internal class TestRemoveStopWords : TestSerializer<RemoveStopWords>(RemoveStopWords) {

    override val items = listOf(
        RemoveStopWords.Boolean(boolean) to JsonLiteral(boolean),
        RemoveStopWords.QueryLanguages(
            Afrikaans,
            Albanian
        ) to jsonArray {
            +Afrikaans.raw
            +Albanian.raw
        }
    )
}