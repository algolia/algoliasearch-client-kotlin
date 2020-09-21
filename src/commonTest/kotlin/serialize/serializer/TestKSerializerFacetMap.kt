package serialize.serializer

import attributeA
import attributeB
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.KSerializerFacetMap
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestKSerializerFacetMap : TestSerializer<Map<Attribute, List<Facet>>>(KSerializerFacetMap) {

    override val items = listOf(facets to jsonObject)

    companion object {

        val facets = mapOf(
            attributeA to listOf(Facet("facetA", 0)),
            attributeB to listOf(Facet("facetB", 1), Facet("facetC", 2))
        )
        val jsonObject = buildJsonObject {
            put(
                attributeA.raw,
                buildJsonObject {
                    put("facetA", 0)
                }
            )
            put(
                attributeB.raw,
                buildJsonObject {
                    put("facetB", 1)
                    put("facetC", 2)
                }
            )
        }
    }
}
