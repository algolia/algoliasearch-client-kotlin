package dsl.geosearch

import com.algolia.search.dsl.geosearch.DSLPolygon
import com.algolia.search.helper.and
import com.algolia.search.model.search.Polygon
import shouldEqual
import kotlin.test.Test

internal class TestDSLPolygon {

    @Test
    fun default() {
        val dsl = DSLPolygon {
            +Polygon(0f and 1f, 2f and 3f, 4f and 5f)
        }

        dsl shouldEqual listOf(
            Polygon(0f and 1f, 2f and 3f, 4f and 5f)
        )
    }
}
