package serialize

import boolean
import com.algolia.search.saas.model.BooleanOrQueryLanguages
import com.algolia.search.saas.model.BooleanOrQueryLanguages.Boolean
import com.algolia.search.saas.model.BooleanOrQueryLanguages.QueryLanguages
import com.algolia.search.saas.model.QueryLanguage.Afrikaans
import com.algolia.search.saas.model.QueryLanguage.Albanian
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestBooleanOrQueryLanguages : TestSerializer<BooleanOrQueryLanguages>(BooleanOrQueryLanguages) {

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