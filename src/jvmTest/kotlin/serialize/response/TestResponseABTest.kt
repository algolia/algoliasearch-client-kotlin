package serialize.response

import com.algolia.search.helper.toABTestID
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.model.response.ResponseABTest
import com.algolia.search.serialize.*
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestResponseABTest : TestSerializer<ResponseABTest>(ResponseABTest) {

    override val items = listOf(
        ResponseABTest(
            abTestID = 0L.toABTestID(),
            createdAt = unknown,
            endAt = unknown,
            name = unknown,
            status = ABTestStatus.Failed,
            variantA = TestResponseVariant.item,
            variantB = TestResponseVariant.item,
            clickSignificanceOrNull = 1f,
            conversionSignificanceOrNull = 2f
        ) to json {
            KeyABTestID to 0L
            KeyCreatedAt to unknown
            KeyEndAt to unknown
            KeyName to unknown
            KeyStatus to ABTestStatus.Failed.raw
            KeyVariants to jsonArray {
                +TestResponseVariant.json
                +TestResponseVariant.json
            }
            KeyClickSignificance to 1f
            KeyConversionSignificance to 2f
        }
    )
}