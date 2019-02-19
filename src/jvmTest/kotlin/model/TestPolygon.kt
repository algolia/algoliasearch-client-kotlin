package model

import com.algolia.search.model.enums.Polygon
import com.algolia.search.and
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual

@RunWith(JUnit4::class)
internal class TestPolygon {

    private val point1 = 1f and 2f
    private val point2 = 3f and 4f
    private val point3 = 5f and 6f
    private val point4 = 7f and 8f
    private val polygon = Polygon(point1, point2, point3, point4)

    @Test
    fun points() {
        polygon.point1 shouldEqual point1
        polygon.point2 shouldEqual point2
        polygon.point3 shouldEqual point3
        polygon.raw shouldEqual listOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f)
    }

    @Test
    fun operator() {
        polygon[0] shouldEqual point1
        polygon[1] shouldEqual point2
        polygon[2] shouldEqual point3
        polygon[3] shouldEqual point4
    }

    @Test
    fun operatorThrow() {
        var thrown = false
        try {
            polygon[4]
        } catch (exception: IndexOutOfBoundsException) {
            thrown = true
        }
        thrown shouldEqual true
    }

    @Test
    fun equality() {
        polygon shouldEqual Polygon(1f and 2f, 3f and 4f, 5f and 6f, 7f and 8f)
    }
}