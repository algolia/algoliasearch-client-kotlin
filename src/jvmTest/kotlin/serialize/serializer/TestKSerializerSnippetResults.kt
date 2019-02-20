package serialize.serializer

import attributeA
import attributeB
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.MatchLevel
import com.algolia.search.model.search.SnippetResult
import com.algolia.search.serialize.KSerializerSnippetResults
import com.algolia.search.serialize.KeyMatchLevel
import com.algolia.search.serialize.KeyValue
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestKSerializerSnippetResults : TestSerializer<Map<Attribute, SnippetResult>>(KSerializerSnippetResults) {

    override val items = listOf(
        mapOf(
            attributeA to SnippetResult(unknown, MatchLevel.Full),
            attributeB to SnippetResult(unknown, MatchLevel.None)
        ) to json {
            attributeA.raw to json {
                KeyValue to unknown
                KeyMatchLevel to MatchLevel.Full.raw
            }
            attributeB.raw to json {
                KeyValue to unknown
                KeyMatchLevel to MatchLevel.None.raw
            }
        }
    )
}