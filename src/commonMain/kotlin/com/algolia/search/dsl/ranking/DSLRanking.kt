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
class DSLRanking(
    private val rankingCriteria: MutableList<RankingCriterion> = mutableListOf()
) {

    enum class Modifier {
        Asc,
        Desc
    }

    val Asc = Modifier.Asc
    val Desc = Modifier.Desc
    val Typo = RankingCriterion.Typo
    val Geo = RankingCriterion.Geo
    val Words = RankingCriterion.Words
    val Filters = RankingCriterion.Filters
    val Proximity = RankingCriterion.Proximity
    val Attribute = RankingCriterion.Attribute
    val Exact = RankingCriterion.Exact
    val Custom = RankingCriterion.Custom

    /**
     * Add [this] to [rankingCriteria].
     */
    operator fun RankingCriterion.unaryPlus() {
        rankingCriteria += this
    }

    /**
     * Convenience method.
     */
    operator fun Modifier.invoke(attribute: String): RankingCriterion {
        return invoke(Attribute(attribute))
    }

    /**
     * Create a [RankingCriterion] with [this] [Modifier] to be applied on [attribute].
     */
    operator fun Modifier.invoke(attribute: Attribute): RankingCriterion {
        return when (this) {
            Modifier.Asc -> RankingCriterion.Asc(attribute)
            Modifier.Desc -> RankingCriterion.Desc(attribute)
        }
    }

    companion object : DSL<DSLRanking, List<RankingCriterion>> {

        override operator fun invoke(block: DSLRanking.() -> Unit): List<RankingCriterion> {
            return DSLRanking().apply(block).rankingCriteria
        }
    }
}
