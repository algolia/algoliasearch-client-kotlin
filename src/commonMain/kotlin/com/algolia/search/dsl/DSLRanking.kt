package com.algolia.search.dsl

import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.RankingCriterium


@Suppress("PropertyName")
@DSLParameters
public class DSLRanking {

    private val rankingCriteria: MutableList<RankingCriterium> = mutableListOf()

    public val Asc = ModifierRanking.Asc
    public val Desc = ModifierRanking.Desc
    public val Typo = RankingCriterium.Typo
    public val Geo = RankingCriterium.Geo
    public val Words = RankingCriterium.Words
    public val Filters = RankingCriterium.Filters
    public val Proximity = RankingCriterium.Proximity
    public val Attribute = RankingCriterium.Attribute
    public val Exact = RankingCriterium.Exact
    public val Custom = RankingCriterium.Custom

    public operator fun RankingCriterium.unaryPlus() {
        rankingCriteria += this
    }

    public infix fun String.modify(modifier: ModifierRanking): RankingCriterium {
        return Attribute(this) modify modifier
    }

    public infix fun Attribute.modify(modifier: ModifierRanking): RankingCriterium {
        return when (modifier) {
            ModifierRanking.Asc -> RankingCriterium.Asc(this)
            ModifierRanking.Desc -> RankingCriterium.Desc(this)
        }
    }

    public fun build(): List<RankingCriterium> {
        return rankingCriteria.toList()
    }
}