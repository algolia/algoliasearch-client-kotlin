package serialize

import attributeA
import attributeB
import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.data.FacetStats
import com.algolia.search.saas.serialize.KSerializerFacetStats
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestFacetStats : TestSerializer<Map<Attribute, FacetStats>>(KSerializerFacetStats) {

    override val items = listOf(
        mapOf(
            attributeA to FacetStats(0, 1, 2f, 3f),
            attributeB to FacetStats(1, 2, 3f, 4f)
        )
    )
}