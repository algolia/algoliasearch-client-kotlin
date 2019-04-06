package dsl.ranking

import attributeA
import attributeB
import com.algolia.search.dsl.ranking.DSLCustomRanking
import com.algolia.search.model.settings.CustomRankingCriterium
import shouldEqual
import kotlin.test.Test


internal class TestDSLCustomRanking {

    @Test
    fun modifier() {
        val dsl = DSLCustomRanking {
            +Asc("attributeA")
            +Desc(attributeB)
        }

        dsl shouldEqual listOf(
            CustomRankingCriterium.Asc(attributeA),
            CustomRankingCriterium.Desc(attributeB)
        )
    }
}