package serialize.settings

import attributeA
import attributeB
import com.algolia.search.model.settings.SearchableAttribute
import com.algolia.search.model.settings.SearchableAttribute.Default
import com.algolia.search.model.settings.SearchableAttribute.Unordered
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer

internal class TestSearchableAttribute : TestSerializer<SearchableAttribute>(SearchableAttribute) {

    override val items = listOf(
        Unordered(attributeA) to JsonPrimitive("${Key.Unordered}($attributeA)"),
        Default(attributeA) to JsonPrimitive(attributeA.raw),
        Default(attributeA, attributeB) to JsonPrimitive("$attributeA, $attributeB")
    )
}
