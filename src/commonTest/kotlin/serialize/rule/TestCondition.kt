package serialize.rule

import attributeA
import com.algolia.search.model.rule.Alternatives
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Pattern
import com.algolia.search.serialize.JsonNoDefaults
import com.algolia.search.serialize.KeyAlternatives
import com.algolia.search.serialize.KeyAnchoring
import com.algolia.search.serialize.KeyIs
import com.algolia.search.serialize.KeyPattern
import serialize.TestSerializer
import unknown

internal class TestCondition : TestSerializer<Condition>(Condition.serializer(), JsonNoDefaults) {

    override val items = listOf(
        Condition(
            anchoring = Anchoring.Is,
            pattern = Pattern.Facet(attributeA),
            alternative = Alternatives.True
        ) to json {
            KeyAnchoring to KeyIs
            KeyPattern to "{facet:$attributeA}"
            KeyAlternatives to true
        },
        Condition(
            anchoring = Anchoring.Is,
            pattern = Pattern.Literal(unknown),
            alternative = Alternatives.False
        ) to json {
            KeyAnchoring to KeyIs
            KeyPattern to unknown
            KeyAlternatives to false
        }
    )
}
