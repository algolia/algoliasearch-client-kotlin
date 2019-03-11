package serialize.serializer

import attributeA
import attributeB
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.HighlightResult
import com.algolia.search.serialize.KSerializerHighlightResults
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import serialize.search.TestHighlightResult


@RunWith(JUnit4::class)
internal class TestKSerializerHighlightResults :
    TestSerializer<Map<Attribute, List<HighlightResult>>>(KSerializerHighlightResults) {

    override val items = listOf(
        item to json
    )

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