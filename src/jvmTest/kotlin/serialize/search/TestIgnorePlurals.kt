package serialize.search

import boolean
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.QueryLanguage
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer


@RunWith(JUnit4::class)
internal class TestIgnorePlurals : TestSerializer<IgnorePlurals>(IgnorePlurals) {

    override val items = listOf(
        IgnorePlurals.Boolean(boolean) to JsonLiteral(boolean),
        IgnorePlurals.QueryLanguages(
            QueryLanguage.Afrikaans,
            QueryLanguage.Albanian
        ) to jsonArray {
            +QueryLanguage.Afrikaans.raw
            +QueryLanguage.Albanian.raw
        }
    )
}