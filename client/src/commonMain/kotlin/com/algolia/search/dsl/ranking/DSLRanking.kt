package com.algolia.search.dsl.ranking

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.RankingCriterion

/**
 * DSL for building a [List] of [RankingCriterion].
 */
@Suppress("PropertyName")
@DSLParameters
public class DSLRanking(
    private val rankingCriteria: MutableList<RankingCriterion> = mutableListOf()
) {

    public enum class Modifier {
        Asc,
        Desc
    }

    public val Asc: Modifier = Modifier.Asc
    public val Desc: Modifier = Modifier.Desc
    public val Typo: RankingCriterion.Typo = RankingCriterion.Typo
    public val Geo: RankingCriterion.Geo = RankingCriterion.Geo
    public val Words: RankingCriterion.Words = RankingCriterion.Words
    public val Filters: RankingCriterion.Filters = RankingCriterion.Filters
    public val Proximity: RankingCriterion.Proximity = RankingCriterion.Proximity
    public val Attribute: RankingCriterion.Attribute = RankingCriterion.Attribute
    public val Exact: RankingCriterion.Exact = RankingCriterion.Exact
    public val Custom: RankingCriterion.Custom = RankingCriterion.Custom

    /**
     * Add [this] to [rankingCriteria].
     */
    public operator fun RankingCriterion.unaryPlus() {
        rankingCriteria += this
    }

    /**
     * Convenience method.
     */
    public operator fun Modifier.invoke(attribute: String): RankingCriterion {
        return invoke(Attribute(attribute))
    }

    /**
     * Create a [RankingCriterion] with [this] [Modifier] to be applied on [attribute].
     */
    public operator fun Modifier.invoke(attribute: Attribute): RankingCriterion {
        return when (this) {
            Modifier.Asc -> RankingCriterion.Asc(attribute)
            Modifier.Desc -> RankingCriterion.Desc(attribute)
        }
    }

    public companion object : DSL<DSLRanking, List<RankingCriterion>> {

        override operator fun invoke(block: DSLRanking.() -> Unit): List<RankingCriterion> {
            return DSLRanking().apply(block).rankingCriteria
        }
    }
}
