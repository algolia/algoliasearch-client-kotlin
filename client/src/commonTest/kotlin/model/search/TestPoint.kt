package model.search

import com.algolia.search.helper.and
import com.algolia.search.model.search.Point
import shouldEqual
import kotlin.test.Test

internal class TestPoint {

    @Test
    fun dx() {
        val point = 1f and 2f

        point shouldEqual Point(1f, 2f)
    }

    @Test
    fun point() {
        val point = Point(1f, 2f)

        point.raw shouldEqual listOf(1f, 2f)
        1f shouldEqual point.latitude
        2f shouldEqual point.longitude
    }
}
