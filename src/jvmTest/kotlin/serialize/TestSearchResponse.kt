package serialize

import attributeB
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.SearchResponse
import com.algolia.search.serialize.*
import indexA
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestSearchResponse : TestSerializer<SearchResponse>(SearchResponse.serializer()) {

    override val items = listOf(
        SearchResponse(
            indexName = indexA,
            hits = listOf(TestSearchResponseHit.hit),
            cursor = Cursor(unknown),
            facets = TestFacets.facets,
            facetStats = mapOf(attributeB to TestFacetStats.facetStats),
            processingTimeMS = 0,
            hitsPerPage = 2,
            page = 3,
            query = unknown,
            nbPages = 4,
            nbHits = 0,
            exhaustiveFacetsCount = true,
            params = unknown
        ) to json {
            KeyIndex to indexA.raw
            KeyHits to jsonArray { +TestSearchResponseHit.json }
            KeyNbHits to 0
            KeyProcessingTimeMS to 0
            KeyQuery to unknown
            KeyParams to unknown
            KeyFacetsStats to json {
                attributeB.raw to TestFacetStats.json
            }
            KeyCursor to unknown
            KeyHitsPerPage to 2
            KeyPage to 3
            KeyNbPages to 4
            KeyFacets to TestFacets.json
            KeyExhaustiveFacetsCount to true
        }
    )
}