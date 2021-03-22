package dsl.rule

import com.algolia.search.dsl.rule.DSLConditions
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Pattern
import shouldEqual
import kotlin.test.Test

internal class TestDSLConditions {

    @Test
    fun default() {
        val dsl = DSLConditions {
            +condition(Contains, Literal("value1"))
            +condition(Is, Literal("value2"))
        }

        dsl shouldEqual listOf(
            Condition(Anchoring.Contains, Pattern.Literal("value1")),
            Condition(Anchoring.Is, Pattern.Literal("value2"))
        )
    }

    @Test
    fun anchoring() {
        DSLConditions {
            Is shouldEqual Anchoring.Is
            StartsWith shouldEqual Anchoring.StartsWith
            EndsWith shouldEqual Anchoring.EndsWith
            Contains shouldEqual Anchoring.Contains
        }
    }
}
