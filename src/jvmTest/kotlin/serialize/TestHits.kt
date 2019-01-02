package serialize

import attributeA
import attributeB
import com.algolia.search.saas.data.*
import indexA
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestHits : TestSerializer<Hits>(Hits.serializer()) {

    override val items = listOf(
        Hits(
            index = indexA,
            hits = listOf(Hit("serialized", JsonLiteral("serialized"))),
            cursor = Cursor("cursor"),
            facets = mapOf(attributeA to listOf(Facet("facet", 0))),
            facetStats = mapOf(attributeB to FacetStats(0, 1, 2f, 3f)),
            processingTimeMS = 0,
            offset = 1,
            hitsPerPage = 2,
            page = 3,
            nbPages = 4,
            exhaustiveFacetsCount = true,
            exhaustiveNbHits = false,
            params = "params"
        )
    )
}