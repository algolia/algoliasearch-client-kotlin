package serialize.search

import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Language
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import serialize.TestSerializer

internal class TestIgnorePlurals : TestSerializer<IgnorePlurals>(IgnorePlurals) {

    override val items = listOf(
        IgnorePlurals.True to JsonPrimitive(true),
        IgnorePlurals.False to JsonPrimitive(false),
        IgnorePlurals.QueryLanguages(
            Language.Afrikaans,
            Language.Albanian
        ) to buildJsonArray {
            add(Language.Afrikaans.raw)
            add(Language.Albanian.raw)
        }
    )
}
