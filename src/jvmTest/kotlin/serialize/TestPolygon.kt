package serialize

import com.algolia.search.saas.data.Polygon
import com.algolia.search.saas.to
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestPolygon : TestSerializer<Polygon>(Polygon) {

    override val items = listOf(
        Polygon(
            1f to 2f,
            3f to 4f,
            5f to 6f,
            7f to 8f,
            9f to 10f,
            11f to 12f,
            13f to 14f,
            15f to 16f
        )
    )
}