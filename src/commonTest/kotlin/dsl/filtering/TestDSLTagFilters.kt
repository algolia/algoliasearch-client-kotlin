package dsl.filtering

import attributeA
import attributeB
import com.algolia.search.dsl.filtering.DSLTagFilters
import shouldEqual
import kotlin.test.Test


internal class TestDSLTagFilters {

    @Test
    fun and() {
        val dsl = DSLTagFilters().apply {
            and {
                +tag(attributeA.raw)
                +tag(attributeB.raw)
            }
        }

        dsl.build() shouldEqual listOf(listOf("_tags:\"attributeA\""), listOf("_tags:\"attributeB\""))
    }

    @Test
    fun or() {
        val dsl = DSLTagFilters().apply {
            or {
                +tag(attributeA.raw)
                +tag(attributeB.raw)
            }
            or {
                +tag(attributeA.raw)
            }
        }

        dsl.build() shouldEqual listOf(
            listOf("_tags:\"attributeA\"", "_tags:\"attributeB\""),
            listOf("_tags:\"attributeA\"")
        )
    }

    @Test
    fun emptyGroups() {
        val dsl = DSLTagFilters().apply {
            and { }
            or { }
        }

        dsl.build() shouldEqual listOf()
    }
}