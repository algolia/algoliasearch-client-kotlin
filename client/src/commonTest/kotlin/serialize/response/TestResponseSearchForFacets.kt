package serialize.response

import com.algolia.search.model.response.ResponseSearchForFacets
import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.internal.KeyCount
import com.algolia.search.serialize.internal.KeyExhaustiveFacetsCount
import com.algolia.search.serialize.internal.KeyFacetHits
import com.algolia.search.serialize.internal.KeyHighlighted
import com.algolia.search.serialize.internal.KeyProcessingTimeMS
import com.algolia.search.serialize.internal.KeyValue
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import unknown

internal class TestResponseSearchForFacets : TestSerializer<ResponseSearchForFacets>(
    ResponseSearchForFacets.serializer()
) {

    override val items = listOf(
        ResponseSearchForFacets(
            facets = listOf(
                Facet(unknown, 0, "hello")
            ),
            exhaustiveFacetsCount = true,
            processingTimeMS = 0
        ) to buildJsonObject {
            put(
                KeyFacetHits,
                buildJsonArray {
                    add(
                        buildJsonObject {
                            put(KeyValue, unknown)
                            put(KeyCount, 0)
                            put(KeyHighlighted, "hello")
                        }
                    )
                }
            )
            put(KeyExhaustiveFacetsCount, true)
            put(KeyProcessingTimeMS, 0)
        }
    )
}
