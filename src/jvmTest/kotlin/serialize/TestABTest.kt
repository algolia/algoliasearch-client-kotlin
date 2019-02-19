package serialize

import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.Variant
import com.algolia.search.serialize.JsonNoNulls
import com.algolia.search.serialize.KeyEndAt
import com.algolia.search.serialize.KeyName
import com.algolia.search.serialize.KeyVariants
import indexA
import indexB
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestABTest : TestSerializer<ABTest>(ABTest) {

    private val abTest = ABTest(
        unknown,
        unknown,
        Variant(indexA, 40),
        Variant(indexB, 60, unknown)
    )

    override val items = listOf(
        abTest to json {
            KeyEndAt to unknown
            KeyName to unknown
            KeyVariants to jsonArray {
                +JsonNoNulls.toJson(Variant.serializer(), abTest.variantA)
                +JsonNoNulls.toJson(Variant.serializer(), abTest.variantB)
            }
        }
    )
}