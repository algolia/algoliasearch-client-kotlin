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

        1f shouldEqual boundingBox.point1
        2f shouldEqual boundingBox.point2
        3f shouldEqual boundingBox.point3
        4f shouldEqual boundingBox.point4
        listOf(1f, 2f, 3f, 4f) shouldEqual boundingBox.raw
    }
}