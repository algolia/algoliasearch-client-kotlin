package dsl.ranking

import attributeA
import attributeB
import com.algolia.search.dsl.ranking.DSLRanking
import com.algolia.search.model.settings.RankingCriterium
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
            RankingCriterium.Typo,
            RankingCriterium.Geo,
            RankingCriterium.Words,
            RankingCriterium.Filters,
            RankingCriterium.Proximity,
            RankingCriterium.Attribute,
            RankingCriterium.Exact,
            RankingCriterium.Custom
        )
    }

    @Test
    fun modifier() {
        val dsl = DSLRanking {
            +Asc("attributeA")
            +Desc(attributeB)
        }

        dsl shouldEqual listOf(
            RankingCriterium.Asc(attributeA),
            RankingCriterium.Desc(attributeB)
        )
    }
}