package serialize

import attributeA
import attributeB
import attributeC
import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.data.Facet
import com.algolia.search.saas.serialize.KSerializerFacets
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestFacets : TestSerializer<Map<Attribute, List<Facet>>>(KSerializerFacets) {

    override val items = listOf(
        mapOf(attributeA to listOf()),
        mapOf(attributeB to listOf(Facet("facetA", 0))),
        mapOf(attributeC to listOf(Facet("facetB", 1), Facet("facetC", 2)))
    )
}