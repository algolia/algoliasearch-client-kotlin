package serialize.response

import com.algolia.search.model.response.ResponseVariant
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.KeyAverageClickPosition
import com.algolia.search.serialize.KeyClickCount
import com.algolia.search.serialize.KeyClickThroughRate
import com.algolia.search.serialize.KeyConversionCount
import com.algolia.search.serialize.KeyConversionRate
import com.algolia.search.serialize.KeyCustomSearchParameters
import com.algolia.search.serialize.KeyDescription
import com.algolia.search.serialize.KeyIndex
import com.algolia.search.serialize.KeyNoResultCount
import com.algolia.search.serialize.KeySearchCount
import com.algolia.search.serialize.KeyTrackedSearchCount
import com.algolia.search.serialize.KeyTrafficPercentage
import com.algolia.search.serialize.KeyUserCount
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
            clickCount = 0,
            conversionCount = 1,
            description = unknown,
            indexName = indexA,
            trafficPercentage = 2,
            conversionRateOrNull = 3f,
            noResultCountOrNull = 4,
            averageClickPositionOrNull = 5,
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
            put(KeyAverageClickPosition, 5)
            put(KeySearchCount, 6)
            put(KeyTrackedSearchCount, 7)
            put(KeyUserCount, 8)
            put(KeyClickThroughRate, 9f)
            put(KeyCustomSearchParameters, buildJsonObject { })
        }
    }
}
