package dsl.filtering

import attributeA
import com.algolia.search.dsl.filtering.DSLGroupFilter
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.NumericOperator
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestDSLFilterGroupFilter {

    @Test
    fun dx() {
        val dsl = DSLGroupFilter {
            tag(unknown)
            facet(attributeA, 0)
            range(attributeA, 0 until 2)
            comparison(attributeA, LessOrEquals, 0)
        }

        dsl shouldEqual setOf(
            Filter.Tag(unknown),
            Filter.Facet(attributeA, 0),
            Filter.Numeric(attributeA, 0 until 2),
            Filter.Numeric(attributeA, NumericOperator.LessOrEquals, 0)
        )
    }
}
