package serialize.settings

import com.algolia.search.model.rule.FacetValuesOrder
import com.algolia.search.model.rule.SortRule
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import string

internal class TestFacetValuesOrder : TestSerializer<FacetValuesOrder>(FacetValuesOrder.serializer()) {

    override val items = listOf(
        FacetValuesOrder(
            sortRemainingBy = SortRule.Alpha
        ) to buildJsonObject {
            put(Key.SortRemainingBy, Key.Alpha)
        },
        FacetValuesOrder(
            sortRemainingBy = SortRule.Count,
            order = listOf(string)
        ) to buildJsonObject {
            put(Key.SortRemainingBy, Key.Count)
            put(Key.Order, buildJsonArray { add(string) })
        },
        FacetValuesOrder(
            sortRemainingBy = SortRule.Hidden,
            order = emptyList()
        ) to buildJsonObject {
            put(Key.SortRemainingBy, Key.Hidden)
            put(Key.Order, buildJsonArray {  })
        }
    )
}
