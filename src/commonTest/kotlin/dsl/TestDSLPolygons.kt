package dsl

import com.algolia.search.dsl.geosearch.DSLPolygons
import com.algolia.search.helper.and
import com.algolia.search.model.search.Polygon
import shouldEqual
import kotlin.test.Test


internal class TestDSLPolygons {

    @Test
    fun default() {
        val dsl = DSLPolygons().apply {
            +Polygon(0f and 1f, 2f and 3f, 4f and 5f)
        }

        dsl.build() shouldEqual listOf(
            Polygon(0f and 1f, 2f and 3f, 4f and 5f)
        )
    }
}