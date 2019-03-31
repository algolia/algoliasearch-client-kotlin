package com.algolia.search.dsl.ranking

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.CustomRankingCriterium


@Suppress("PropertyName")
@DSLParameters
public class DSLCustomRanking(
    private val customRankingCriteria: MutableList<CustomRankingCriterium> = mutableListOf()
) {

    public enum class Modifier {
        Asc,
        Desc
    }

    public val Asc = Modifier.Asc
    public val Desc = Modifier.Desc

    public operator fun CustomRankingCriterium.unaryPlus() {
        customRankingCriteria += this
    }

    public infix fun String.modify(modifier: Modifier): CustomRankingCriterium {
        return Attribute(this) modify modifier
    }

    public infix fun Attribute.modify(modifier: Modifier): CustomRankingCriterium {
        return when (modifier) {
            Modifier.Asc -> CustomRankingCriterium.Asc(this)
            Modifier.Desc -> CustomRankingCriterium.Desc(this)
        }
    }

    public fun build(): List<CustomRankingCriterium> {
        return customRankingCriteria.toList()
    }
}
