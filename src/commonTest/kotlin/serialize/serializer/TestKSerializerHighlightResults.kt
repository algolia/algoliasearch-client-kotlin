package serialize.serializer

import com.algolia.search.model.search.HighlightResult
import com.algolia.search.serialize.KSerializerHighlightResults
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer
import serialize.search.TestHighlightResult


internal class TestKSerializerHighlightResults :
    TestSerializer<List<HighlightResult>>(KSerializerHighlightResults) {

    override val items = listOf(
        listOf(TestHighlightResult.item) to TestHighlightResult.json,
        listOf(
            TestHighlightResult.item,
            TestHighlightResult.item
        ) to jsonArray {
            +TestHighlightResult.json
            +TestHighlightResult.json
        }
    )
}