package serialize.response

import com.algolia.search.helper.internal.DateISO8601
import com.algolia.search.helper.toABTestID
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.model.response.ResponseABTest
import com.algolia.search.serialize.internal.KeyABTestID
import com.algolia.search.serialize.internal.KeyClickSignificance
import com.algolia.search.serialize.internal.KeyConversionSignificance
import com.algolia.search.serialize.internal.KeyCreatedAt
import com.algolia.search.serialize.internal.KeyEndAt
import com.algolia.search.serialize.internal.KeyName
import com.algolia.search.serialize.internal.KeyStatus
import com.algolia.search.serialize.internal.KeyVariants
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
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
        ) to buildJsonObject {
            put(KeyABTestID, 0L)
            put(KeyCreatedAt, unknown)
            put(KeyEndAt, date)
            put(KeyName, unknown)
            put(KeyStatus, ABTestStatus.Failed.raw)
            put(
                KeyVariants,
                buildJsonArray {
                    add(TestResponseVariant.json)
                    add(TestResponseVariant.json)
                }
            )
            put(KeyClickSignificance, 1f)
            put(KeyConversionSignificance, 2f)
        }
    )
}
