package serialize.serializer

import attributeA
import attributeB
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.KSerializerFacetMap
import kotlinx.serialization.json.json
import serialize.TestSerializer


internal class TestKSerializerFacetMap : TestSerializer<Map<Attribute, List<Facet>>>(KSerializerFacetMap) {

    override val items = listOf(
        facets to json
    )

    companion object {

        val facets = mapOf(
            attributeA to listOf(Facet("facetA", 0)),
            attributeB to listOf(Facet("facetB", 1), Facet("facetC", 2))
        )
        val json = json {
            attributeA.raw to json {
                "facetA" to 0
            }
            attributeB.raw to json {
                "facetB" to 1
                "facetC" to 2
            }
        }
    }
}