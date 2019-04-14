package com.algolia.search.dsl.ranking

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.CustomRankingCriterion


@Suppress("PropertyName")
@DSLParameters
public class DSLCustomRanking(
    private val customRankingCriteria: MutableList<CustomRankingCriterion> = mutableListOf()
) {

    public enum class Modifier {
        Asc,
        Desc
    }

    public val Asc = Modifier.Asc
    public val Desc = Modifier.Desc

    public operator fun CustomRankingCriterion.unaryPlus() {
        customRankingCriteria += this
    }

    operator fun Modifier.invoke(attribute: String): CustomRankingCriterion {
        return invoke(Attribute(attribute))
    }

    operator fun Modifier.invoke(attribute: Attribute): CustomRankingCriterion {
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
