package com.algolia.search.dsl.ranking

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.RankingCriterium


@Suppress("PropertyName")
@DSLParameters
public class DSLRanking(
    private val rankingCriteria: MutableList<RankingCriterium> = mutableListOf()
) {

    public enum class Modifier {
        Asc,
        Desc
    }

    public val Asc = Modifier.Asc
    public val Desc = Modifier.Desc
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

    operator fun Modifier.invoke(attribute: String): RankingCriterium {
        return invoke(Attribute(attribute))
    }

    operator fun Modifier.invoke(attribute: Attribute): RankingCriterium {
        return when (this) {
            Modifier.Asc -> RankingCriterium.Asc(attribute)
            Modifier.Desc -> RankingCriterium.Desc(attribute)
        }
    }

    public companion object : DSL<DSLRanking, List<RankingCriterium>> {

        override operator fun invoke(block: DSLRanking.() -> Unit): List<RankingCriterium> {
            return DSLRanking().apply(block).rankingCriteria
        }
    }
}