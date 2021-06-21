package com.algolia.search.dsl.filtering

import com.algolia.search.model.filter.Filter

/**
 * DSL for building a [Filter.Tag].
 * [Filter by numeric][https://www.algolia.com/doc/guides/managing-results/refine-results/filtering/how-to/filter-by-tags/]
 */
public interface DSLTag {

    public operator fun Filter.Tag.unaryPlus()

    /**
     * Create a [Filter.Tag] with an [value].
     */
    public fun tag(value: String, isNegated: Boolean = false) {
        +Filter.Tag(value, isNegated)
    }
}
