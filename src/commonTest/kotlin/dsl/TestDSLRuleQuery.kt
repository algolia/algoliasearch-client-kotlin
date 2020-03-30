package dsl

import com.algolia.search.dsl.ruleQuery
import kotlin.test.Test
import shouldNotBeNull

internal class TestDSLRuleQuery {

    @Test
    fun default() {
        val ruleQuery = ruleQuery {
            anchoring = Contains
        }

        ruleQuery.anchoring.shouldNotBeNull()
    }
}
