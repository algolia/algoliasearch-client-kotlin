package data

import client.data.Polygon
import client.to
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual

@RunWith(JUnit4::class)
internal class TestPolygon {

    private val point1 = 1f to 2f
    private val point2 = 3f to 4f
    private val point3 = 5f to 6f
    private val point4 = 7f to 8f
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
        polygon shouldEqual Polygon(1f to 2f, 3f to 4f, 5f to 6f, 7f to 8f)
    }
}