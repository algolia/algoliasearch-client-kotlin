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
public class DSLCustomRanking(
    private val customRankingCriteria: MutableList<CustomRankingCriterion> = mutableListOf()
) {

    public enum class Modifier {
        Asc,
        Desc
    }

    public val Asc: Modifier = Modifier.Asc
    public val Desc: Modifier = Modifier.Desc

    /**
     * Add [this] to [customRankingCriteria].
     */
    public operator fun CustomRankingCriterion.unaryPlus() {
        customRankingCriteria += this
    }

    /**
     * Convenience method.
     */
    public operator fun Modifier.invoke(attribute: String): CustomRankingCriterion {
        return invoke(Attribute(attribute))
    }

    /**
     * Create a [CustomRankingCriterion] using [this] [Modifier] to be applied on [attribute].
     */
    public operator fun Modifier.invoke(attribute: Attribute): CustomRankingCriterion {
        return when (this) {
            Modifier.Asc -> CustomRankingCriterion.Asc(attribute)
            Modifier.Desc -> CustomRankingCriterion.Desc(attribute)
        }
    }

    public companion object : DSL<DSLCustomRanking, List<CustomRankingCriterion>> {

        override operator fun invoke(block: DSLCustomRanking.() -> Unit): List<CustomRankingCriterion> {
            return DSLCustomRanking().apply(block).customRankingCriteria
        }
    }
}
