package dsl.geosearch

import com.algolia.search.dsl.geosearch.DSLBoundingBox
import com.algolia.search.helper.and
import com.algolia.search.model.search.BoundingBox
import shouldEqual
import kotlin.test.Test

internal class TestDSLBoundingBox {

    @Test
    fun default() {
        val dsl = DSLBoundingBox {
            +BoundingBox(0f and 1f, 2f and 3f)
        }

        dsl shouldEqual listOf(
            BoundingBox(0f and 1f, 2f and 3f)
        )
    }
}
