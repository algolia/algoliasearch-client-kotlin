package serialize.response

import com.algolia.search.model.response.ResponseSearchForFacetValues
import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.*
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer
import unknown


internal class TestResponseSearchForFacetValues : TestSerializer<ResponseSearchForFacetValues>(
    ResponseSearchForFacetValues.serializer()
) {

    override val items = listOf(
        ResponseSearchForFacetValues(
            facets = listOf(
                Facet(unknown, 0)
            ),
            exhaustiveFacetsCount = true,
            processingTimeMS = 0
        ) to json {
            KeyFacetHits to jsonArray {
                +json {
                    KeyValue to unknown
                    KeyCount to 0
                }
            }
            KeyExhaustiveFacetsCount to true
            KeyProcessingTimeMS to 0
        }
    )
}