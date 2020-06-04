package dsl.filtering

import com.algolia.search.dsl.filtering.DSLGroupTag
import com.algolia.search.model.filter.Filter
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestDSLFilterGroupTag {

    @Test
    fun tag() {
        val dsl = DSLGroupTag {
            tag(unknown)
        }

        dsl shouldEqual setOf(Filter.Tag(unknown))
    }
}
