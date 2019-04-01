package dsl.geosearch

import com.algolia.search.dsl.geosearch.DSLBoundingBoxes
import com.algolia.search.helper.and
import com.algolia.search.model.search.BoundingBox
import shouldEqual
import kotlin.test.Test


internal class TestDSLBoundingBoxes {

    @Test
    fun default() {
        val dsl = DSLBoundingBoxes().apply {
            +BoundingBox(0f and 1f, 2f and 3f)
        }

        dsl.build() shouldEqual listOf(
            BoundingBox(0f and 1f, 2f and 3f)
        )
    }
}