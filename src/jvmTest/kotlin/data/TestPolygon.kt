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
        point1 shouldEqual polygon.point1
        point2 shouldEqual polygon.point2
        point3 shouldEqual polygon.point3
        listOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f) shouldEqual polygon.raw
    }

    @Test
    fun operator() {
        point1 shouldEqual polygon[0]
        point2 shouldEqual polygon[1]
        point3 shouldEqual polygon[2]
        point4 shouldEqual polygon[3]
    }

    @Test
    fun operatorThrow() {
        var thrown = false
        try {
            polygon[4]
        } catch (exception: IndexOutOfBoundsException) {
            thrown = true
        }
        true shouldEqual thrown
    }

    @Test
    fun equality() {
        Polygon(1f to 2f, 3f to 4f, 5f to 6f, 7f to 8f) shouldEqual polygon
    }
}