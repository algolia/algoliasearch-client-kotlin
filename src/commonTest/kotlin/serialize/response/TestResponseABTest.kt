package serialize.response

import com.algolia.search.helper.DateISO8601
import com.algolia.search.helper.toABTestID
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.model.response.ResponseABTest
import com.algolia.search.serialize.KeyABTestID
import com.algolia.search.serialize.KeyClickSignificance
import com.algolia.search.serialize.KeyConversionSignificance
import com.algolia.search.serialize.KeyCreatedAt
import com.algolia.search.serialize.KeyEndAt
import com.algolia.search.serialize.KeyName
import com.algolia.search.serialize.KeyStatus
import com.algolia.search.serialize.KeyVariants
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer
import unknown

internal class TestResponseABTest : TestSerializer<ResponseABTest>(ResponseABTest) {

    private val date = DateISO8601.format(0)

    override val items = listOf(
        ResponseABTest(
            abTestID = 0L.toABTestID(),
            createdAt = unknown,
            endAt = ClientDate(date),
            name = unknown,
            status = ABTestStatus.Failed,
            variantA = TestResponseVariant.item,
            variantB = TestResponseVariant.item,
            clickSignificanceOrNull = 1f,
            conversionSignificanceOrNull = 2f
        ) to json {
            KeyABTestID to 0L
            KeyCreatedAt to unknown
            KeyEndAt to date
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
