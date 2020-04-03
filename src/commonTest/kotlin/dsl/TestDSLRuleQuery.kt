package dsl

import com.algolia.search.dsl.ruleQuery
import shouldNotBeNull
import kotlin.test.Test

internal class TestDSLRuleQuery {

    @Test
    fun default() {
        val ruleQuery = ruleQuery {
            anchoring = Contains
        }

        ruleQuery.anchoring.shouldNotBeNull()
    }
}
