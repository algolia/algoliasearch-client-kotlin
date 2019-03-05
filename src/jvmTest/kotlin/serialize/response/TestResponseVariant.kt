package serialize.response

import com.algolia.search.model.response.ResponseVariant
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import indexA
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestResponseVariant : TestSerializer<ResponseVariant>(ResponseVariant.serializer(), Json.noDefaults) {

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

        val json = json {
            KeyClickCount to 0
            KeyConversionCount to 1
            KeyDescription to unknown
            KeyIndex to indexA.raw
            KeyTrafficPercentage to 2
            KeyConversionRate to 3f
            KeyNoResultCount to 4
            KeyAverageClickPosition to 5
            KeySearchCount to 6
            KeyTrackedSearchCount to 7
            KeyUserCount to 8
            KeyClickThroughRate to 9f
            KeyCustomSearchParameters to kotlinx.serialization.json.json { }
        }
    }
}