package serialize

import client.data.Point
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer


@RunWith(JUnit4::class)
internal class TestPoint : TestSerializer<Point>(Point) {

    private val point = Point(1f, 2f)
    private val jsonArray = jsonArray {
        +(1f as Number)
        +(2f as Number)
    }

    override val item = listOf(
        point to jsonArray
    )
    override val items = listOf(
        listOf(
            point,
            point
        ) to jsonArray {
            +jsonArray
            +jsonArray
        }
    )
}