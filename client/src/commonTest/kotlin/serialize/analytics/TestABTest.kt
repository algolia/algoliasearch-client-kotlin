package serialize.analytics

import com.algolia.search.helper.internal.DateISO8601
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
import indexA
import indexB
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import unknown

internal class TestABTest : TestSerializer<ABTest>(ABTest, JsonNoDefaults) {

    private val date = DateISO8601.format(0)
    private val abTest = ABTest(
        name = unknown,
        endAt = ClientDate(date),
        variantA = Variant(indexA, 40),
        variantB = Variant(indexB, 60, Query(), unknown)
    )

    override val items = listOf(
        abTest to buildJsonObject {
            put(Key.Name, unknown)
            put(Key.EndAt, date)
            put(
                Key.Variants,
                buildJsonArray {
                    add(JsonNoDefaults.encodeToJsonElement(Variant.serializer(), abTest.variantA))
                    add(JsonNoDefaults.encodeToJsonElement(Variant.serializer(), abTest.variantB))
                }
            )
        }
    )
}
