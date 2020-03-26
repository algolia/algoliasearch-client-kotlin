package dsl.ranking

import attributeA
import attributeB
import com.algolia.search.dsl.ranking.DSLCustomRanking
import com.algolia.search.model.settings.CustomRankingCriterion
import kotlin.test.Test
import shouldEqual

internal class TestDSLCustomRanking {

    @Test
    fun modifier() {
        val dsl = DSLCustomRanking {
            +Asc("attributeA")
            +Desc(attributeB)
        }

        dsl shouldEqual listOf(
            CustomRankingCriterion.Asc(attributeA),
            CustomRankingCriterion.Desc(attributeB)
        )
    }
}
