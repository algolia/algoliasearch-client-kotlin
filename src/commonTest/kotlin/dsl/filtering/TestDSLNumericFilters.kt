package dsl.filtering

import attributeA
import attributeB
import com.algolia.search.dsl.filtering.DSLNumericFilters
import shouldEqual
import kotlin.test.Test


internal class TestDSLNumericFilters {

    @Test
    fun and() {
        val dsl = DSLNumericFilters().apply {
            and {
                +range(attributeA, 0..2)
                +range(attributeA, 1..2)
            }
        }

        dsl.build() shouldEqual listOf(listOf("\"attributeA\":0 TO 2"), listOf("\"attributeA\":1 TO 2"))
    }

    @Test
    fun or() {
        val dsl = DSLNumericFilters().apply {
            or {
                +range(attributeA, 0..2)
                +range(attributeB, 1..2)
            }
            or {
                +range(attributeA, 0..2)
            }
        }

        dsl.build() shouldEqual listOf(
            listOf("\"attributeA\":0 TO 2", "\"attributeB\":1 TO 2"),
            listOf("\"attributeA\":0 TO 2")
        )
    }

    @Test
    fun emptyGroups() {
        val dsl = DSLNumericFilters().apply {
            and { }
            or { }
        }

        dsl.build() shouldEqual listOf()
    }
}