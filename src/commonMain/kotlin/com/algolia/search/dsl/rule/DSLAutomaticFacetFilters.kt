package com.algolia.search.dsl.rule

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.rule.AutomaticFacetFilters

/**
 * DSL for building a [List] of [AutomaticFacetFilters].
 */
@DSLParameters
public class DSLAutomaticFacetFilters(
    private val automaticFacetFilters: MutableList<AutomaticFacetFilters> = mutableListOf()
) {

    /**
     * Convenience method.
     */
    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    /**
     * Convenience method.
     */
    public operator fun Attribute.unaryPlus() {
        +AutomaticFacetFilters(this)
    }

    /**
     * Add [this] to [automaticFacetFilters].
     */
    public operator fun AutomaticFacetFilters.unaryPlus() {
        automaticFacetFilters += this
    }

    /**
     * Convenience method.
     */
    public operator fun String.invoke(score: Int? = null, disjunctive: Boolean? = null): AutomaticFacetFilters {
        return Attribute(this).invoke(score, disjunctive)
    }

    /**
     * Create an [AutomaticFacetFilters] using [this] and optionals [score] and [disjunctive].
     */
    public operator fun Attribute.invoke(score: Int? = null, disjunctive: Boolean? = null): AutomaticFacetFilters {
        return AutomaticFacetFilters(this, score, disjunctive)
    }

    public companion object : DSL<DSLAutomaticFacetFilters, List<AutomaticFacetFilters>> {

        override operator fun invoke(block: DSLAutomaticFacetFilters.() -> Unit): List<AutomaticFacetFilters> {
            return DSLAutomaticFacetFilters().apply(block).automaticFacetFilters
        }
    }
}
