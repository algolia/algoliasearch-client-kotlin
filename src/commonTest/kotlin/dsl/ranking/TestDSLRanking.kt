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
        val dsl = DSLRanking().apply {
            +Typo
            +Geo
            +Words
            +Filters
            +Proximity
            +Attribute
            +Exact
            +Custom
        }

        dsl.build() shouldEqual listOf(
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
        val dsl = DSLRanking().apply {
            +("attributeA" modify Asc)
            +(attributeB modify Desc)
        }

        dsl.build() shouldEqual listOf(
            RankingCriterium.Asc(attributeA),
            RankingCriterium.Desc(attributeB)
        )
    }
}