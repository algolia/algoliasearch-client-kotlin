package serialize.rule

import com.algolia.search.model.rule.Alternatives
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer

internal class TestAlternatives : TestSerializer<Alternatives>(Alternatives) {

    override val items = listOf(
        Alternatives.True to JsonPrimitive(true),
        Alternatives.False to JsonPrimitive(false)
    )
}
