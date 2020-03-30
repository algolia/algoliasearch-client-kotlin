package dsl

import com.algolia.search.dsl.DSLStrings
import kotlin.test.Test
import shouldEqual
import unknown

internal class TestDSLStrings {

    @Test
    fun default() {
        val dsl = DSLStrings {
            +unknown
        }

        dsl shouldEqual listOf(unknown)
    }
}
