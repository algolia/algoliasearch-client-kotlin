package serialize.rule

import attributeA
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Pattern
import com.algolia.search.serialize.JsonNoNulls
import com.algolia.search.serialize.KeyAnchoring
import com.algolia.search.serialize.KeyIs
import com.algolia.search.serialize.KeyPattern
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestCondition : TestSerializer<Condition>(Condition.serializer(), JsonNoNulls) {

    override val items = listOf(
        Condition(
            anchoring = Anchoring.Is,
            pattern = Pattern.Facet(attributeA)
        ) to json {
            KeyAnchoring to KeyIs
            KeyPattern to "{facet:$attributeA}"
        },
        Condition(
            anchoring = Anchoring.Is,
            pattern = Pattern.Literal(unknown)
        ) to json {
            KeyAnchoring to KeyIs
            KeyPattern to unknown
        }
    )
}