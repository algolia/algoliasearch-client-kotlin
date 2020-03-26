package serialize

import attributeA
import com.algolia.search.model.Attribute
import kotlinx.serialization.json.JsonLiteral

internal class TestAttribute : TestSerializer<Attribute>(Attribute) {

    override val items = listOf(
        attributeA to JsonLiteral(attributeA.raw)
    )
}
