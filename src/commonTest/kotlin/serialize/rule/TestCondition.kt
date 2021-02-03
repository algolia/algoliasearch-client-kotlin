package serialize.rule

import attributeA
import com.algolia.search.model.rule.Alternatives
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Pattern
import com.algolia.search.serialize.KeyAlternatives
import com.algolia.search.serialize.KeyAnchoring
import com.algolia.search.serialize.KeyFilters
import com.algolia.search.serialize.KeyIs
import com.algolia.search.serialize.KeyPattern
import com.algolia.search.serialize.internal.JsonNoDefaults
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
            put(KeyAnchoring, KeyIs)
            put(KeyPattern, "{facet:$attributeA}")
            put(KeyAlternatives, true)
            put(KeyFilters, "brand:samsung")
        },
        Condition(
            anchoring = Anchoring.Is,
            pattern = Pattern.Literal(unknown),
            alternative = Alternatives.False,
            filters = "brand:samsung",
        ) to buildJsonObject {
            put(KeyAnchoring, KeyIs)
            put(KeyPattern, unknown)
            put(KeyAlternatives, false)
            put(KeyFilters, "brand:samsung")
        }
    )
}
