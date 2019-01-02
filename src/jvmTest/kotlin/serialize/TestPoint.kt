package serialize

import com.algolia.search.saas.data.Point
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestPoint : TestSerializer<Point>(Point) {

    override val items = listOf(
        Point(1f, 2f)
    )
}