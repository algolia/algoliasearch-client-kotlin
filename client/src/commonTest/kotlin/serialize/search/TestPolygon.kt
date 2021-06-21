package serialize.search

import com.algolia.search.helper.and
import com.algolia.search.model.search.Polygon
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import serialize.TestSerializer

internal class TestPolygon : TestSerializer<Polygon>(Polygon) {

    override val items = listOf(
        polygon to json
    )

    companion object {

        val polygon = Polygon(
            1f and 2f,
            3f and 4f,
            5f and 6f,
            7f and 8f,
            9f and 10f,
            11f and 12f,
            13f and 14f,
            15f and 16f
        )
        val json = buildJsonArray {
            add((1f as Number))
            add((2f as Number))
            add((3f as Number))
            add((4f as Number))
            add((5f as Number))
            add((6f as Number))
            add((7f as Number))
            add((8f as Number))
            add((9f as Number))
            add((10f as Number))
            add((11f as Number))
            add((12f as Number))
            add((13f as Number))
            add((14f as Number))
            add((15f as Number))
            add((16f as Number))
        }
    }
}
