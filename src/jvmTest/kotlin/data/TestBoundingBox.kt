package data

import com.algolia.search.saas.data.BoundingBox
import com.algolia.search.saas.to
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestBoundingBox {

    @Test
    fun boundingBox() {
        val boundingBox = BoundingBox(1f to 2f, 3f to 4f)

        boundingBox.point1 shouldEqual 1f to 2f
        boundingBox.point2 shouldEqual 3f to 4f
        boundingBox.raw shouldEqual listOf(1f, 2f, 3f, 4f)
    }
}