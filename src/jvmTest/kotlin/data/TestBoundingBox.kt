package data

import client.data.BoundingBox
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestBoundingBox {

    @Test
    fun boundingBox() {
        val boundingBox = BoundingBox(1f, 2f, 3f, 4f)

        boundingBox.point1 shouldEqual 1f
        boundingBox.point2 shouldEqual 2f
        boundingBox.point3 shouldEqual 3f
        boundingBox.point4 shouldEqual 4f
        boundingBox.raw shouldEqual listOf(1f, 2f, 3f, 4f)
    }
}