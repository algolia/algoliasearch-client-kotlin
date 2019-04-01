package dsl.filtering

import com.algolia.search.dsl.filtering.DSLGroupTag
import com.algolia.search.dsl.filtering.Filter
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestDSLGroupTag {

    @Test
    fun tag() {
        val dsl = DSLGroupTag {
            +tag(unknown)
        }

        dsl shouldEqual setOf(Filter.Tag(unknown))
    }
}