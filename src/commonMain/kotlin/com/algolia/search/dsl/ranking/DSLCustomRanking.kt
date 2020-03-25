package com.algolia.search.dsl.ranking

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.CustomRankingCriterion

/**
 * DSL for building a [List] of [CustomRankingCriterion].
 */
@Suppress("PropertyName")
@DSLParameters
class DSLCustomRanking(
    private val customRankingCriteria: MutableList<CustomRankingCriterion> = mutableListOf()
) {

    enum class Modifier {
        Asc,
        Desc
    }

    val Asc = Modifier.Asc
    val Desc = Modifier.Desc

    /**
     * Add [this] to [customRankingCriteria].
     */
    operator fun CustomRankingCriterion.unaryPlus() {
        customRankingCriteria += this
    }

    /**
     * Convenience method.
     */
    operator fun Modifier.invoke(attribute: String): CustomRankingCriterion {
        return invoke(Attribute(attribute))
    }

    /**
     * Create a [CustomRankingCriterion] using [this] [Modifier] to be applied on [attribute].
     */
    operator fun Modifier.invoke(attribute: Attribute): CustomRankingCriterion {
        return when (this) {
            Modifier.Asc -> CustomRankingCriterion.Asc(attribute)
            Modifier.Desc -> CustomRankingCriterion.Desc(attribute)
        }
    }

    companion object : DSL<DSLCustomRanking, List<CustomRankingCriterion>> {

        override operator fun invoke(block: DSLCustomRanking.() -> Unit): List<CustomRankingCriterion> {
            return DSLCustomRanking().apply(block).customRankingCriteria
        }
    }
}
