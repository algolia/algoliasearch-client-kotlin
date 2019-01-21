package serialize

import attributeA
import com.algolia.search.saas.data.QueryRule
import com.algolia.search.saas.serialize.KeyAnchoring
import com.algolia.search.saas.serialize.KeyIs
import com.algolia.search.saas.serialize.KeyPattern
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestQueryRuleCondition : TestSerializer<QueryRule.Condition>(QueryRule.Condition) {

    override val items = listOf(
        QueryRule.Condition(
            anchoring = QueryRule.Anchoring.Is,
            pattern = QueryRule.Pattern.Facet(attributeA)
        ) to json {
            KeyAnchoring to KeyIs
            KeyPattern to "{facet:$attributeA}"
        },
        QueryRule.Condition(
            anchoring = QueryRule.Anchoring.Is,
            pattern = QueryRule.Pattern.Literal(unknown)
        ) to json {
            KeyAnchoring to KeyIs
            KeyPattern to unknown
        }
    )
}