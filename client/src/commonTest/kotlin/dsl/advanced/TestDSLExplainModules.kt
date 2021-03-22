package dsl.advanced

import com.algolia.search.dsl.advanced.DSLExplainModules
import com.algolia.search.dsl.advanced.DSLResponseFields
import com.algolia.search.model.search.ExplainModule
import shouldEqual
import kotlin.test.Test

internal class TestDSLExplainModules {

    @Test
    fun default() {
        val dsl = DSLExplainModules {
            +MatchAlternatives
        }

        dsl shouldEqual listOf(
            ExplainModule.MatchAlternatives
        )
    }

    @Test
    fun responseFields() {
        DSLResponseFields {
            ExplainModule.MatchAlternatives shouldEqual ExplainModule.MatchAlternatives
        }
    }
}
