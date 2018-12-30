package data

import client.data.Point
import client.to
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestPoint {

    @Test
    fun dx() {
        val point = 1f to 2f

        Point(1f, 2f) shouldEqual point
    }

    @Test
    fun point() {
        val point = Point(1f, 2f)

        listOf(1f, 2f) shouldEqual point.raw
        point.latitude shouldEqual 1f
        point.longitude shouldEqual 2f
    }
}