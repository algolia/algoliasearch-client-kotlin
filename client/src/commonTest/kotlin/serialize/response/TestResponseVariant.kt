package serialize.response

import com.algolia.search.model.response.ResponseVariant
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.KeyAverageClickPosition
import com.algolia.search.serialize.internal.KeyClickCount
import com.algolia.search.serialize.internal.KeyClickThroughRate
import com.algolia.search.serialize.internal.KeyConversionCount
import com.algolia.search.serialize.internal.KeyConversionRate
import com.algolia.search.serialize.internal.KeyCustomSearchParameters
import com.algolia.search.serialize.internal.KeyDescription
import com.algolia.search.serialize.internal.KeyIndex
import com.algolia.search.serialize.internal.KeyNoResultCount
import com.algolia.search.serialize.internal.KeySearchCount
import com.algolia.search.serialize.internal.KeyTrackedSearchCount
import com.algolia.search.serialize.internal.KeyTrafficPercentage
import com.algolia.search.serialize.internal.KeyUserCount
import com.algolia.search.serialize.internal.JsonNoDefaults
import indexA
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import unknown

internal class TestResponseVariant : TestSerializer<ResponseVariant>(ResponseVariant.serializer(), JsonNoDefaults) {

    override val items = listOf(
        item to json
    )

    companion object {

        val item = ResponseVariant(
            clickCountOrNull = 0,
            conversionCountOrNull = 1,
            descriptionOrNull = unknown,
            indexName = indexA,
            trafficPercentage = 2,
            conversionRateOrNull = 3f,
            noResultCountOrNull = 4,
            averageClickPositionOrNull = 5f,
            searchCountOrNull = 6,
            trackedSearchCountOrNull = 7,
            userCountOrNull = 8,
            clickThroughRateOrNull = 9f,
            customSearchParametersOrNull = Query()
        )

        val json = buildJsonObject {
            put(KeyClickCount, 0)
            put(KeyConversionCount, 1)
            put(KeyDescription, unknown)
            put(KeyIndex, indexA.raw)
            put(KeyTrafficPercentage, 2)
            put(KeyConversionRate, 3f)
            put(KeyNoResultCount, 4)
            put(KeyAverageClickPosition, 5f)
            put(KeySearchCount, 6)
            put(KeyTrackedSearchCount, 7)
            put(KeyUserCount, 8)
            put(KeyClickThroughRate, 9f)
            put(KeyCustomSearchParameters, buildJsonObject { })
        }
    }
}
