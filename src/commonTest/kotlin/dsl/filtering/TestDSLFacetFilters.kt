package dsl.filtering

import attributeA
import attributeB
import com.algolia.search.dsl.filtering.DSLFacetFilters
import shouldEqual
import kotlin.test.Test


internal class TestDSLFacetFilters {

    @Test
    fun and() {
        val dsl = DSLFacetFilters().apply {
            and {
                +facet(attributeA, 0)
                +facet(attributeA, 1)
            }
        }

        dsl.build() shouldEqual listOf(listOf("\"attributeA\":0"), listOf("\"attributeA\":1"))
    }

    @Test
    fun or() {
        val dsl = DSLFacetFilters().apply {
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
        val dsl = DSLFacetFilters().apply {
            and { }
            or { }
        }

        dsl.build() shouldEqual listOf()
    }
}