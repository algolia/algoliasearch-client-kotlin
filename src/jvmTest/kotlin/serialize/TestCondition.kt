package serialize

import attributeA
import com.algolia.search.saas.model.query_rule.Anchoring
import com.algolia.search.saas.model.query_rule.Condition
import com.algolia.search.saas.model.query_rule.Pattern
import com.algolia.search.saas.serialize.KeyAnchoring
import com.algolia.search.saas.serialize.KeyIs
import com.algolia.search.saas.serialize.KeyPattern
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestCondition : TestSerializer<Condition>(Condition) {

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