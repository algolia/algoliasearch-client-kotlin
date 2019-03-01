package serialize.response

import com.algolia.search.model.response.ResponseSearchForFacetValue
import com.algolia.search.serialize.KeyExhaustiveFacetsCount
import com.algolia.search.serialize.KeyFacetHits
import com.algolia.search.serialize.KeyProcessingTimeMS
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer


@RunWith(JUnit4::class)
internal class TestResponseSearchForFacetValue : TestSerializer<ResponseSearchForFacetValue>(
    ResponseSearchForFacetValue.serializer()
) {

    override val items = listOf(
        ResponseSearchForFacetValue(
            facetHits = listOf(),
            exhaustiveFacetsCount = true,
            processingTimeMS = 0
        ) to json {
            KeyFacetHits to jsonArray { }
            KeyExhaustiveFacetsCount to true
            KeyProcessingTimeMS to 0
        }
    )
}