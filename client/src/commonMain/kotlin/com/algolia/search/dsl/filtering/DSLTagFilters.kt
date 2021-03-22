package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup

/**
 * DSL for building a [List] of [FilterGroup] of [Filter.Tag].
 */
@DSLParameters
public class DSLTagFilters(
    private val groups: MutableSet<FilterGroup<Filter.Tag>> = mutableSetOf()
) {

    /**
     * Add [this] to [groups] if it is not empty.
     */
    public operator fun FilterGroup<Filter.Tag>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    /**
     * Create and add a [FilterGroup.And.Tag] which can only contain [Filter.Tag] to [groups].
     */
    public fun and(block: DSLGroupTag.() -> Unit) {
        +FilterGroup.And.Tag(DSLGroupTag(block))
    }

    /**
     * Create and add a [FilterGroup.Or.Tag] which can only contain [Filter.Tag] to [groups].
     */
    public fun or(block: DSLGroupTag.() -> Unit) {
        +FilterGroup.Or.Tag(DSLGroupTag(block))
    }

    public companion object : DSL<DSLTagFilters, Set<FilterGroup<Filter.Tag>>> {

        override operator fun invoke(block: DSLTagFilters.() -> Unit): Set<FilterGroup<Filter.Tag>> {
            return DSLTagFilters().apply(block).groups
        }
    }
}
