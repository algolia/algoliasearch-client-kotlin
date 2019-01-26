package serialize

import attributeA
import com.algolia.search.saas.model.query_rule.Pattern
import com.algolia.search.saas.model.query_rule.Pattern.Facet
import com.algolia.search.saas.model.query_rule.Pattern.Literal
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestPattern : TestSerializer<Pattern>(Pattern) {

    override val items = listOf(
        Literal(unknown) to JsonLiteral(unknown),
        Facet(attributeA) to JsonLiteral("{facet:$attributeA}")
    )
}