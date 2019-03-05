package serialize.response

import attributeA
import attributeB
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Highlight
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.serialize.KSerializerSnippets
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.Key_SnippetResult
import kotlinx.serialization.internal.HashMapSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import serialize.search.TestHighlight
import unknown


@RunWith(JUnit4::class)
internal class TestResponseSearchHit : TestSerializer<ResponseSearch.Hit>(ResponseSearch.Hit) {

    override val items = listOf(
        hit to json
    )

    companion object {

        val highlights = mapOf(attributeA to TestHighlight.highlightResult)
        val snippets = mapOf(attributeA to unknown, attributeB to unknown)
        val json = json {
            Key_HighlightResult to Json.plain.toJson(
                HashMapSerializer(Attribute, Highlight.serializer()),
                highlights
            )
            Key_SnippetResult to Json.plain.toJson(KSerializerSnippets,
                snippets
            )
            attributeA to unknown
            attributeB to unknown
        }
        val hit = ResponseSearch.Hit(json)
    }
}