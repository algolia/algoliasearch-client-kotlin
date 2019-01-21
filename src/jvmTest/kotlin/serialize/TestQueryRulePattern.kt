package serialize

import attributeA
import com.algolia.search.saas.data.QueryRule
import com.algolia.search.saas.data.QueryRule.Pattern.Facet
import com.algolia.search.saas.data.QueryRule.Pattern.Literal
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestQueryRulePattern : TestSerializer<QueryRule.Pattern>(QueryRule.Pattern) {

    override val items = listOf(
        Literal(unknown) to JsonLiteral(unknown),
        Facet(attributeA) to JsonLiteral("{facet:$attributeA}")
    )
}