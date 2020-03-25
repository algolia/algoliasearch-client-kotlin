package dsl.rule

import attributeA
import attributeB
import com.algolia.search.dsl.rule.DSLAutomaticFacetFilters
import com.algolia.search.model.rule.AutomaticFacetFilters
import shouldEqual
import kotlin.test.Test

internal class TestDSLAutomaticFacetFilters {

    @Test
    fun default() {
        DSLAutomaticFacetFilters {
            +"attributeA"
            +attributeB
        }
    }

    @Test
    fun operators() {
        val dsl = DSLAutomaticFacetFilters {
            +"attributeA"(10)
            +attributeA(10, false)
            +attributeB(disjunctive = true)
        }

        dsl shouldEqual listOf(
            AutomaticFacetFilters(attributeA, 10),
            AutomaticFacetFilters(attributeA, 10, false),
            AutomaticFacetFilters(attributeB, disjunctive = true)
        )
    }
}
