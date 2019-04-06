package com.algolia.search.dsl.ranking

import com.algolia.search.dsl.DSL
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

    operator fun Modifier.invoke(attribute: String): CustomRankingCriterium {
        return invoke(Attribute(attribute))
    }

    operator fun Modifier.invoke(attribute: Attribute): CustomRankingCriterium {
        return when (this) {
            Modifier.Asc -> CustomRankingCriterium.Asc(attribute)
            Modifier.Desc -> CustomRankingCriterium.Desc(attribute)
        }
    }

    public companion object : DSL<DSLCustomRanking, List<CustomRankingCriterium>> {

        override operator fun invoke(block: DSLCustomRanking.() -> Unit): List<CustomRankingCriterium> {
            return DSLCustomRanking().apply(block).customRankingCriteria
        }
    }
}
