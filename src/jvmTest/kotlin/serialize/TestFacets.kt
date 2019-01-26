package serialize

import attributeB
import com.algolia.search.saas.model.Attribute
import com.algolia.search.saas.model.search.Facet
import com.algolia.search.saas.serialize.KSerializerFacets
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestFacets : TestSerializer<Map<Attribute, List<Facet>>>(KSerializerFacets) {

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