package serialize.response

import attributeA
import attributeB
import com.algolia.search.model.Attribute
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.search.HighlightResult
import com.algolia.search.model.search.MatchLevel
import com.algolia.search.model.search.SnippetResult
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.KSerializerSnippetResults
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.Key_SnippetResult
import kotlinx.serialization.internal.HashMapSerializer
import kotlinx.serialization.json.json
import serialize.TestSerializer
import serialize.search.TestHighlightResult
import unknown


internal class TestResponseSearchHit : TestSerializer<ResponseSearch.Hit>(ResponseSearch.Hit) {

    override val items = listOf(
        hit to json
    )

    companion object {

        private val highlights = mapOf(attributeA to TestHighlightResult.item)
        private val snippets = mapOf(
            attributeA to listOf(SnippetResult(unknown, MatchLevel.None)),
            attributeB to listOf(SnippetResult(unknown, MatchLevel.None))
        )
        val json = json {
            Key_HighlightResult to Json.toJson(
                HashMapSerializer(Attribute, HighlightResult.serializer()),
                highlights
            )
            Key_SnippetResult to Json.toJson(KSerializerSnippetResults, snippets)
            attributeA to unknown
            attributeB to unknown
        }
        val hit = ResponseSearch.Hit(json)
    }
}