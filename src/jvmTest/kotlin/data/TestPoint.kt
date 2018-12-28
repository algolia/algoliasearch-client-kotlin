package data

import client.data.Point
import client.to
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestPoint {

    @Test
    fun dx() {
        val point = 1f to 2f

        assertEquals(Point(1f, 2f), point)
    }

    @Test
    fun point() {
        val point = Point(1f, 2f)

        assertEquals(listOf(1f, 2f), point.asList)
        assertEquals(point.latitude, 1f)
        assertEquals(point.longitude, 2f)
    }
}