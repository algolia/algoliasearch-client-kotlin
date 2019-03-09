package serialize.serializer

import attributeA
import attributeB
import com.algolia.search.serialize.KSerializerHighlightResults
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.search.TestHighlightResult
import shouldEqual


@RunWith(JUnit4::class)
internal class TestKSerializerHighlightResults {

    @Test
    fun test() {
        val serialized = Json.plain.stringify(KSerializerHighlightResults, item)
        val deserialized = Json.plain.parse(KSerializerHighlightResults, serialized)

        deserialized shouldEqual item
    }

    companion object {

        val item = mapOf(
            attributeA to listOf(TestHighlightResult.item),
            attributeB to listOf(
                TestHighlightResult.item,
                TestHighlightResult.item
            )
        )

        val json = json {
            attributeA.raw to TestHighlightResult.json
            attributeB.raw to jsonArray {
                +TestHighlightResult.json
                +TestHighlightResult.json
            }
        }
    }
}