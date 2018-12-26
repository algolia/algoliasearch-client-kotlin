import client.data.Point
import client.data.Polygon
import client.data.to
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class TestPolygon {

    private val point1 = 1f to 2f
    private val point2 = 3f to 4f
    private val point3 = 5f to 6f
    private val point4 = 7f to 8f
    private val polygon = Polygon(point1, point2, point3, point4)

    @Test
    fun points() {
        assertEquals(point1, polygon.point1)
        assertEquals(point2, polygon.point2)
        assertEquals(point3, polygon.point3)
        assertEquals(listOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f), polygon.floats)
    }

    @Test
    fun operator() {
        assertEquals(point1, polygon[0])
        assertEquals(point2, polygon[1])
        assertEquals(point3, polygon[2])
        assertEquals(point4, polygon[3])
    }

    @Test
    fun operatorThrow() {
        var thrown = false
        try {
            polygon[4]
        } catch (exception: IndexOutOfBoundsException) {
            thrown = true
        }
        assertEquals(true, thrown)
    }

    @Test
    fun equality() {
        assertEquals(Polygon(1f to 2f, 3f to 4f, 5f to 6f, 7f to 8f), polygon)
    }


    @Test
    fun point() {
        val point = 1f to 2f

        assertEquals(listOf(1f, 2f), point.floats)
        assertEquals(Point(1f, 2f), point)
    }
}