package dsl.ranking

import attributeA
import attributeB
import com.algolia.search.dsl.ranking.DSLRanking
import com.algolia.search.model.settings.RankingCriterion
import shouldEqual
import kotlin.test.Test

internal class TestDSLRanking {

    @Test
    fun default() {
        val dsl = DSLRanking {
            +Typo
            +Geo
            +Words
            +Filters
            +Proximity
            +Attribute
            +Exact
            +Custom
        }

        dsl shouldEqual listOf(
            RankingCriterion.Typo,
            RankingCriterion.Geo,
            RankingCriterion.Words,
            RankingCriterion.Filters,
            RankingCriterion.Proximity,
            RankingCriterion.Attribute,
            RankingCriterion.Exact,
            RankingCriterion.Custom
        )
    }

    @Test
    fun modifier() {
        val dsl = DSLRanking {
            +Asc("attributeA")
            +Desc(attributeB)
        }

        dsl shouldEqual listOf(
            RankingCriterion.Asc(attributeA),
            RankingCriterion.Desc(attributeB)
        )
    }
}
