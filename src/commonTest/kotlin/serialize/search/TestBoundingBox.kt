package serialize.search

import com.algolia.search.helper.and
import com.algolia.search.model.search.BoundingBox
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import serialize.TestSerializer

internal class TestBoundingBox : TestSerializer<BoundingBox>(BoundingBox) {

    override val items = listOf(
        boundingBox to json
    )

    companion object {

        val boundingBox = BoundingBox(1f and 2f, 3f and 4f)
        val json = buildJsonArray {
            add((1f as Number))
            add((2f as Number))
            add((3f as Number))
            add((4f as Number))
        }
    }
}
