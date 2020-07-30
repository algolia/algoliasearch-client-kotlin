package serialize.search

import com.algolia.search.model.search.Point
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import serialize.TestSerializer

internal class TestPoint : TestSerializer<Point>(Point) {

    override val items = listOf(point to jsonObject)

    companion object {

        val point = Point(1f, 2f)
        val jsonObject = buildJsonArray {
            add((1f as Number))
            add((2f as Number))
        }
    }
}
