package serialize.rule

import com.algolia.search.model.rule.Alternatives
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer


internal class TestAlternatives : TestSerializer<Alternatives>(Alternatives) {

    override val items = listOf(
        Alternatives.True to JsonLiteral(true),
        Alternatives.False to JsonLiteral(false)
    )
}