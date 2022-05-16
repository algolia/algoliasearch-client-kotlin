package serialize.response

import com.algolia.search.helper.internal.DateISO8601
import com.algolia.search.helper.toABTestID
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.model.response.ResponseABTest
import com.algolia.search.serialize.internal.Key
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
            put(Key.ABTestID, 0L)
            put(Key.CreatedAt, unknown)
            put(Key.EndAt, date)
            put(Key.Name, unknown)
            put(Key.Status, ABTestStatus.Failed.raw)
            put(
                Key.Variants,
                buildJsonArray {
                    add(TestResponseVariant.json)
                    add(TestResponseVariant.json)
                }
            )
            put(Key.ClickSignificance, 1f)
            put(Key.ConversionSignificance, 2f)
        }
    )
}
