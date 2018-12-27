package data

import client.data.BoundingBox
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestBoundingBox {

    @Test
    fun boundingBox() {
        val boundingBox = BoundingBox(1f, 2f, 3f, 4f)

        assertEquals(1f, boundingBox.point1)
        assertEquals(2f, boundingBox.point2)
        assertEquals(3f, boundingBox.point3)
        assertEquals(4f, boundingBox.point4)
        assertEquals(listOf(1f, 2f, 3f, 4f), boundingBox.floats)
    }
}