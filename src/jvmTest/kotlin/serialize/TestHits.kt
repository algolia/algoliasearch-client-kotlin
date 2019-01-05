package serialize

import attributeA
import attributeB
import com.algolia.search.saas.data.*
import indexA
import kotlinx.serialization.internal.HashMapSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestHits : TestSerializer<Hits>(Hits.serializer()) {

    private val highlights = mapOf(attributeA to TestHighlightResult.highlightResult)
    private val highlightsJson = Json.nonstrict.toJson(
        highlights,
        HashMapSerializer(Attribute, HighlightResult.serializer())
    )
    private val hitsJson = json {
        "_highlightResult" to highlightsJson
    }

    override val items = listOf(
        Hits(
            index = indexA,
            hits = listOf(Hit(hitsJson, highlights)),
            cursor = Cursor("cursor"),
            facets = TestFacets.facets,
            facetStats = mapOf(attributeB to TestFacetStats.facetStats),
            processingTimeMS = 0,
            offset = 1,
            hitsPerPage = 2,
            page = 3,
            query = "query",
            nbPages = 4,
            length = 0,
            nbHits = 0,
            exhaustiveFacetsCount = true,
            exhaustiveNbHits = false,
            params = "params"
        ) to json {
            "index" to indexA.raw
            "hits" to jsonArray {
                +json {
                    "_highlightResult" to highlightsJson
                }
            }
            "nbHits" to 0
            "processingTimeMS" to 0
            "exhaustiveNbHits" to false
            "query" to "query"
            "params" to "params"
            "facets_stats" to json {
                attributeB.raw to TestFacetStats.json
            }
            "cursor" to "cursor"
            "hitsPerPage" to 2
            "page" to 3
            "nbPages" to 4
            "offset" to 1
            "length" to 0
            "facets" to TestFacets.json
            "exhaustiveFacetsCount" to true
        }
    )
}