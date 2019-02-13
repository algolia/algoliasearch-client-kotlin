package serialize

import com.algolia.search.model.enums.Polygon
import com.algolia.search.and
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
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
        val json = jsonArray {
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
    }
}