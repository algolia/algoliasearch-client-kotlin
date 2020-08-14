package serialize.response

import attributeA
import attributeB
import com.algolia.search.model.Attribute
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.search.HighlightResult
import com.algolia.search.model.search.MatchLevel
import com.algolia.search.model.search.MatchedGeoLocation
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.RankingInfo
import com.algolia.search.model.search.SnippetResult
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.Key_DistinctSeqID
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.Key_RankingInfo
import com.algolia.search.serialize.Key_SnippetResult
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import serialize.search.TestHighlightResult
import unknown

internal class TestResponseSearchHit : TestSerializer<ResponseSearch.Hit>(ResponseSearch.Hit) {

    override val items = listOf(
        hit to jsonObject
    )

    companion object {

        private val highlights = mapOf(attributeA to TestHighlightResult.item)
        private val rankingInfo = RankingInfo(
            promoted = true,
            nbExactWords = 0,
            nbTypos = 1,
            geoDistance = 2,
            geoPrecision = 3,
            matchedGeoLocation = MatchedGeoLocation(Point(0f, 1f), 2),
            firstMatchedWord = 4,
            proximityDistance = 5,
            filters = 6,
            userScore = 7,
            words = 8
        )
        private val snippets = mapOf(
            attributeA to listOf(SnippetResult(unknown, MatchLevel.None)),
            attributeB to listOf(SnippetResult(unknown, MatchLevel.None))
        )
        val jsonObject = buildJsonObject {
            put(Key_DistinctSeqID, 0)
            put(
                Key_HighlightResult,
                Json.encodeToJsonElement(MapSerializer(Attribute, HighlightResult.serializer()), highlights)
            )
            put(
                Key_SnippetResult,
                Json.encodeToJsonElement(MapSerializer(Attribute, ListSerializer(SnippetResult.serializer())), snippets)
            )
            attributeA to unknown
            attributeB to unknown
            put(Key_RankingInfo, Json.encodeToJsonElement(RankingInfo.serializer(), rankingInfo))
        }
        val hit = ResponseSearch.Hit(jsonObject)
    }
}
