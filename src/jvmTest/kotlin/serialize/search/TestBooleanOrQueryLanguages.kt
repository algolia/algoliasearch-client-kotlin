package serialize.search

import boolean
import com.algolia.search.model.search.BooleanOrQueryLanguages
import com.algolia.search.model.search.BooleanOrQueryLanguages.Boolean
import com.algolia.search.model.search.BooleanOrQueryLanguages.QueryLanguages
import com.algolia.search.model.search.QueryLanguage.Afrikaans
import com.algolia.search.model.search.QueryLanguage.Albanian
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer


@RunWith(JUnit4::class)
internal class TestBooleanOrQueryLanguages : TestSerializer<BooleanOrQueryLanguages>(
    BooleanOrQueryLanguages
) {

    override val items = listOf(
        Boolean(boolean) to JsonLiteral(boolean),
        QueryLanguages(
            Afrikaans,
            Albanian
        ) to jsonArray {
            +Afrikaans.raw
            +Albanian.raw
        }
    )
}