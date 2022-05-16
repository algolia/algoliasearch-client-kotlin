package serialize.rule

import attributeA
import com.algolia.search.model.rule.Alternatives
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Pattern
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import unknown

internal class TestCondition : TestSerializer<Condition>(Condition.serializer(), JsonNoDefaults) {

    override val items = listOf(
        Condition(
            anchoring = Anchoring.Is,
            pattern = Pattern.Facet(attributeA),
            alternative = Alternatives.True,
            filters = "brand:samsung",
        ) to buildJsonObject {
            put(Key.Anchoring, Key.Is)
            put(Key.Pattern, "{facet:$attributeA}")
            put(Key.Alternatives, true)
            put(Key.Filters, "brand:samsung")
        },
        Condition(
            anchoring = Anchoring.Is,
            pattern = Pattern.Literal(unknown),
            alternative = Alternatives.False,
            filters = "brand:samsung",
        ) to buildJsonObject {
            put(Key.Anchoring, Key.Is)
            put(Key.Pattern, unknown)
            put(Key.Alternatives, false)
            put(Key.Filters, "brand:samsung")
        }
    )
}
