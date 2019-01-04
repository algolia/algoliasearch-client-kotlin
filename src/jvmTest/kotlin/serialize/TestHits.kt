package serialize

import attributeA
import attributeB
import com.algolia.search.saas.data.*
import com.algolia.search.saas.serialize.KSerializerHighlights
import highlightResult
import indexA
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonTreeParser
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestHits : TestSerializer<Hits>(Hits.serializer()) {

    private val highlights = mapOf(attributeA to highlightResult)
    private val json = json {
        "_highlightResult" to JsonTreeParser.parse(JSON.stringify(KSerializerHighlights, highlights))
    }

    override val items = listOf(
        Hits(
            index = indexA,
            hits = listOf(Hit(json, highlights)),
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