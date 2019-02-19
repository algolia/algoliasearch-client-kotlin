package serialize

import com.algolia.search.model.search.Point
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestPoint : TestSerializer<Point>(Point) {

    override val items = listOf(
        point to json
    )

    companion object {

        val point = Point(1f, 2f)
        val json = jsonArray {
            +(1f as Number)
            +(2f as Number)
        }
    }
}