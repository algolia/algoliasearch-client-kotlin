package serialize

import client.data.Polygon
import client.to
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestPolygon : TestSerializer<Polygon>(Polygon, Polygon) {

    private val polygon = Polygon(
        1f to 2f,
        3f to 4f,
        5f to 6f,
        7f to 8f,
        9f to 10f,
        11f to 12f,
        13f to 14f,
        15f to 16f
    )
    private val jsonArray = jsonArray {
        +(1f as Number)
        +(2f as Number)
        +(3f as Number)
        +(4f as Number)
        +(5f as Number)
        +(6f as Number)
        +(7f as Number)
        +(8f as Number)
        +(9f as Number)
        +(10f as Number)
        +(11f as Number)
        +(12f as Number)
        +(13f as Number)
        +(14f as Number)
        +(15f as Number)
        +(16f as Number)
    }

    override val item = listOf(
        polygon to jsonArray
    )
    override val items = listOf(
        listOf(
            polygon,
            polygon
        ) to jsonArray {
            +jsonArray
            +jsonArray
        }
    )
}