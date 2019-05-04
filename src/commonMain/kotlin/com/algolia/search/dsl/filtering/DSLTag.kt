package com.algolia.search.dsl.filtering

import com.algolia.search.model.filter.Filter


/**
 * DSL for building a [Filter.Tag].
 * [Filter by numeric][https://www.algolia.com/doc/guides/managing-results/refine-results/filtering/how-to/filter-by-tags/]
 */
public interface DSLTag {

    /**
     * Create a [Filter.Tag] with an [value].
     */
    fun tag(value: String): Filter.Tag {
        return Filter.Tag(value)
    }
}