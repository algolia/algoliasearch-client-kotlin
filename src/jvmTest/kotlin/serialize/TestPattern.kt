package serialize

import attributeA
import com.algolia.search.saas.model.Pattern
import com.algolia.search.saas.model.Pattern.Facet
import com.algolia.search.saas.model.Pattern.Literal
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