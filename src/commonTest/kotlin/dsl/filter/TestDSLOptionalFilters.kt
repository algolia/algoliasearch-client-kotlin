package dsl.filter

import attributeA
import attributeB
import com.algolia.search.dsl.filter.DSLOptionalFilters
import shouldEqual
import kotlin.test.Test


internal class TestDSLOptionalFilters {

    @Test
    fun and() {
        val dsl = DSLOptionalFilters().apply {
            and {
                +facet(attributeA, 0)
                +facet(attributeA, 1)
            }
        }

        dsl.build() shouldEqual listOf(listOf("\"attributeA\":0"), listOf("\"attributeA\":1"))
    }

    @Test
    fun or() {
        val dsl = DSLOptionalFilters().apply {
            or {
                +facet(attributeA, 0)
                +facet(attributeB, 1)
            }
            or {
                +facet(attributeA, 0)
            }
        }

        dsl.build() shouldEqual listOf(
            listOf("\"attributeA\":0", "\"attributeB\":1"),
            listOf("\"attributeA\":0")
        )
    }

    @Test
    fun emptyGroups() {
        val dsl = DSLOptionalFilters().apply {
            and { }
            or { }
        }

        dsl.build() shouldEqual listOf()
    }
}