package serialize.search

import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Language
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer

internal class TestIgnorePlurals : TestSerializer<IgnorePlurals>(IgnorePlurals) {

    override val items = listOf(
        IgnorePlurals.True to JsonLiteral(true),
        IgnorePlurals.False to JsonLiteral(false),
        IgnorePlurals.QueryLanguages(
            Language.Afrikaans,
            Language.Albanian
        ) to jsonArray {
            +Language.Afrikaans.raw
            +Language.Albanian.raw
        }
    )
}
