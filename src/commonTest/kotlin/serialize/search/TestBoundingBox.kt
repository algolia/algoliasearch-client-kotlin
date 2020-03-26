package serialize.search

import com.algolia.search.helper.and
import com.algolia.search.model.search.BoundingBox
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer

internal class TestBoundingBox : TestSerializer<BoundingBox>(BoundingBox) {

    override val items = listOf(
        boundingBox to json
    )

    companion object {

        val boundingBox = BoundingBox(1f and 2f, 3f and 4f)
        val json = jsonArray {
            +(1f as Number)
            +(2f as Number)
            +(3f as Number)
            +(4f as Number)
        }
    }
}
