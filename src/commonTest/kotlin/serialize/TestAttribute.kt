package serialize

import attributeA
import com.algolia.search.model.Attribute
import kotlinx.serialization.json.JsonPrimitive

internal class TestAttribute : TestSerializer<Attribute>(Attribute) {

    override val items = listOf(
        attributeA to JsonPrimitive(attributeA.raw)
    )
}
