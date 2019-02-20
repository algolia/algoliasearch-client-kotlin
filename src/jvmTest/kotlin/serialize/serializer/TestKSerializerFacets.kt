package serialize.serializer

import attributeB
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.KSerializerFacets
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer


@RunWith(JUnit4::class)
internal class TestKSerializerFacets : TestSerializer<Map<Attribute, List<Facet>>>(KSerializerFacets) {

    override val items = listOf(
        facets to json
    )

    companion object {

        val facets = mapOf(attributeB to listOf(Facet("facetA", 0)))
        val json = json {
            attributeB.raw to json {
                "facetA" to 0
            }
        }
    }
}