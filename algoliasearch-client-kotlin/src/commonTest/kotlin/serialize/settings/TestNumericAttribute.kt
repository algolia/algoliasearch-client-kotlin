package serialize.settings

import attributeA
import attributeB
import com.algolia.search.model.settings.NumericAttributeFilter
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer

internal class TestNumericAttribute : TestSerializer<NumericAttributeFilter>(NumericAttributeFilter) {

    override val items = listOf(
        NumericAttributeFilter(attributeA) to JsonPrimitive(attributeA.raw),
        item to json
    )

    companion object {

        val item = NumericAttributeFilter(attributeB, true)
        val json = JsonPrimitive(item.raw)
    }
}
