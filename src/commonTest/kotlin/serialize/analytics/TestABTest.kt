package serialize.analytics

import com.algolia.search.helper.DateISO8601
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.JsonNoDefaults
import com.algolia.search.serialize.KeyEndAt
import com.algolia.search.serialize.KeyName
import com.algolia.search.serialize.KeyVariants
import indexA
import indexB
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer
import unknown


internal class TestABTest : TestSerializer<ABTest>(ABTest, JsonNoDefaults) {

    private val date = DateISO8601.format(0)
    private val abTest = ABTest(
        name = unknown,
        endAt = ClientDate(date),
        variantA = Variant(indexA, 40),
        variantB = Variant(indexB, 60, unknown, Query())
    )

    override val items = listOf(
        abTest to json {
            KeyName to unknown
            KeyEndAt to date
            KeyVariants to jsonArray {
                +JsonNoDefaults.toJson(Variant.serializer(), abTest.variantA)
                +JsonNoDefaults.toJson(Variant.serializer(), abTest.variantB)
            }
        }
    )
}