package com.algolia.search.dsl.rule

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.Promotion

/**
 * DSL for building a [List] of [Promotion].
 */
@DSLParameters
class DSLPromotions(
    private val promotions: MutableList<Promotion> = mutableListOf()
) {

    /**
     * Add [this] to [promotions].
     */
    operator fun Promotion.unaryPlus() {
        promotions += this
    }

    /**
     * Convenience method.
     */
    operator fun String.invoke(position: Int): Promotion {
        return ObjectID(this).invoke(position)
    }

    /**
     * Create a [Promotion] with [this] and [position].
     */
    operator fun ObjectID.invoke(position: Int): Promotion {
        return Promotion(this, position)
    }

    companion object : DSL<DSLPromotions, List<Promotion>> {

        override operator fun invoke(block: DSLPromotions.() -> Unit): List<Promotion> {
            return DSLPromotions().apply(block).promotions
        }
    }
}
