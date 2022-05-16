package serialize.response

import com.algolia.search.model.response.ResponseVariant
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
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
            put(Key.ClickCount, 0)
            put(Key.ConversionCount, 1)
            put(Key.Description, unknown)
            put(Key.Index, indexA.raw)
            put(Key.TrafficPercentage, 2)
            put(Key.ConversionRate, 3f)
            put(Key.NoResultCount, 4)
            put(Key.AverageClickPosition, 5f)
            put(Key.SearchCount, 6)
            put(Key.TrackedSearchCount, 7)
            put(Key.UserCount, 8)
            put(Key.ClickThroughRate, 9f)
            put(Key.CustomSearchParameters, buildJsonObject { })
        }
    }
}
