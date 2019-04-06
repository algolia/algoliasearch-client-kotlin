package com.algolia.search.dsl.rule

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.Promotion


@DSLParameters
public class DSLPromotions(
    private val promotions: MutableList<Promotion> = mutableListOf()
) {

    public operator fun Promotion.unaryPlus() {
        promotions += this
    }

    public operator fun String.invoke(position: Int): Promotion {
        return ObjectID(this).invoke(position)
    }

    public operator fun ObjectID.invoke(position: Int): Promotion {
        return Promotion(this, position)
    }

    public companion object : DSL<DSLPromotions, List<Promotion>> {

        override operator fun invoke(block: DSLPromotions.() -> Unit): List<Promotion> {
            return DSLPromotions().apply(block).promotions
        }
    }
}