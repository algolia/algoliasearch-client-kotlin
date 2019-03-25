package serialize.analytics

import com.algolia.search.helper.DateISO8601
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KeyEndAt
import com.algolia.search.serialize.KeyName
import com.algolia.search.serialize.KeyVariants
import com.algolia.search.serialize.noDefaults
import indexA
import indexB
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer
import unknown


internal class TestABTest : TestSerializer<ABTest>(ABTest, Json.noDefaults) {

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
                +Json.noDefaults.toJson(Variant.serializer(), abTest.variantA)
                +Json.noDefaults.toJson(Variant.serializer(), abTest.variantB)
            }
        }
    )
}