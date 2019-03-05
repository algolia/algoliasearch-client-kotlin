package serialize.analytics

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
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestABTest : TestSerializer<ABTest>(ABTest, Json.noDefaults) {

    private val abTest = ABTest(
        name = unknown,
        endAt = unknown,
        variantA = Variant(indexA, 40),
        variantB = Variant(indexB, 60, unknown, Query())
    )

    override val items = listOf(
        abTest to json {
            KeyName to unknown
            KeyEndAt to unknown
            KeyVariants to jsonArray {
                +Json.noDefaults.toJson(Variant.serializer(), abTest.variantA)
                +Json.noDefaults.toJson(Variant.serializer(), abTest.variantB)
            }
        }
    )
}