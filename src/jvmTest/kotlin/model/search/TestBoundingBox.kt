package model.search

import com.algolia.search.helper.and
import com.algolia.search.model.search.BoundingBox
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestBoundingBox {

    @Test
    fun boundingBox() {
        val boundingBox = BoundingBox(1f and 2f, 3f and 4f)

        boundingBox.point1 shouldEqual (1f and 2f)
        boundingBox.point2 shouldEqual (3f and 4f)
        boundingBox.raw shouldEqual listOf(1f, 2f, 3f, 4f)
    }
}