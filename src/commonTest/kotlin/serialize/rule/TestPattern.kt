package serialize.rule

import attributeA
import com.algolia.search.model.rule.Pattern
import com.algolia.search.model.rule.Pattern.Facet
import com.algolia.search.model.rule.Pattern.Literal
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestPattern : TestSerializer<Pattern>(Pattern) {

    override val items = listOf(
        Literal(unknown) to JsonPrimitive(unknown),
        Facet(attributeA) to JsonPrimitive("{facet:$attributeA}")
    )
}
