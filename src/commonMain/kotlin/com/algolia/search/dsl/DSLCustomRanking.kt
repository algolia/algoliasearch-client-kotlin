package com.algolia.search.dsl

import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.CustomRankingCriterium


@Suppress("PropertyName")
@DSLParameters
public class DSLCustomRanking {

    private val customRankingCriteria: MutableList<CustomRankingCriterium> = mutableListOf()

    public val Asc = ModifierCustomRanking.Asc
    public val Desc = ModifierCustomRanking.Desc

    public operator fun CustomRankingCriterium.unaryPlus() {
        customRankingCriteria += this
    }

    public infix fun String.modify(modifier: ModifierCustomRanking): CustomRankingCriterium {
        return Attribute(this) modify modifier
    }

    public infix fun Attribute.modify(modifier: ModifierCustomRanking): CustomRankingCriterium {
        return when (modifier) {
            ModifierCustomRanking.Asc -> CustomRankingCriterium.Asc(this)
            ModifierCustomRanking.Desc -> CustomRankingCriterium.Desc(this)
        }
    }

    public fun build(): List<CustomRankingCriterium> {
        return customRankingCriteria.toList()
    }
}
