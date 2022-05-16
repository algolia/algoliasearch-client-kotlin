package serialize.response

import com.algolia.search.model.response.ResponseSearchForFacets
import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.internal.Key
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
                Key.FacetHits,
                buildJsonArray {
                    add(
                        buildJsonObject {
                            put(Key.Value, unknown)
                            put(Key.Count, 0)
                            put(Key.Highlighted, "hello")
                        }
                    )
                }
            )
            put(Key.ExhaustiveFacetsCount, true)
            put(Key.ProcessingTimeMS, 0)
        }
    )
}
