package com.algolia.search.dsl.filtering

import com.algolia.search.model.Attribute
import com.algolia.search.model.filter.Filter

/**
 * DSL for building a [Filter.Facet].
 * [Filter by string][https://www.algolia.com/doc/guides/managing-results/refine-results/filtering/how-to/filter-by-string/]
 * [Filter by boolean][https://www.algolia.com/doc/guides/managing-results/refine-results/filtering/how-to/filter-by-boolean/]
 */
public interface DSLFacet {

    public operator fun Filter.Facet.unaryPlus()

    /**
     * Convenience method.
     */
    public fun facet(name: String, value: Number, score: Int? = null, isNegated: Boolean = false) {
        facet(Attribute(name), value, score, isNegated)
    }

    /**
     * Build a [Filter.Facet] using [attribute], a numeric [value] and an optional [score].
     */
    public fun facet(attribute: Attribute, value: Number, score: Int? = null, isNegated: Boolean = false) {
        +Filter.Facet(attribute, value, score, isNegated)
    }

    /**
     * Convenience method.
     */
    public fun facet(name: String, value: String, score: Int? = null, isNegated: Boolean = false) {
        facet(Attribute(name), value, score, isNegated)
    }

    /**
     * Build a [Filter.Facet] using [attribute], a [String] [value] and an optional [score].
     */
    public fun facet(attribute: Attribute, value: String, score: Int? = null, isNegated: Boolean = false) {
        +Filter.Facet(attribute, value, score, isNegated)
    }

    /**
     * Convenience method.
     */
    public fun facet(name: String, value: Boolean, score: Int? = null, isNegated: Boolean = false) {
        facet(Attribute(name), value, score, isNegated)
    }

    /**
     * Build a [Filter.Facet] using [attribute], a [Boolean] [value] and an optional [score].
     */
    public fun facet(attribute: Attribute, value: Boolean, score: Int? = null, isNegated: Boolean = false) {
        +Filter.Facet(attribute, value, score, isNegated)
    }
}
