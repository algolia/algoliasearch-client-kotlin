package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup

/**
 * DSL for building a [List] of [FilterGroup] of [Filter.Numeric].
 */
@DSLParameters
public class DSLNumericFilters(
    private val groups: MutableSet<FilterGroup<Filter.Numeric>> = mutableSetOf()
) {

    /**
     * Add [this] to [groups] if it is not empty.
     */
    public operator fun FilterGroup<Filter.Numeric>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    /**
     * Create and add a [FilterGroup.And.Numeric] which can only contain [Filter.Numeric] to [groups].
     */
    public fun and(block: DSLGroupNumeric.() -> Unit) {
        +FilterGroup.And.Numeric(DSLGroupNumeric(block))
    }

    /**
     * Create and add a [FilterGroup.Or.Numeric] which can only contain [Filter.Numeric] to [groups].
     */
    public fun or(block: DSLGroupNumeric.() -> Unit) {
        +FilterGroup.Or.Numeric(DSLGroupNumeric(block))
    }

    public companion object : DSL<DSLNumericFilters, Set<FilterGroup<Filter.Numeric>>> {

        override operator fun invoke(block: DSLNumericFilters.() -> Unit): Set<FilterGroup<Filter.Numeric>> {
            return DSLNumericFilters().apply(block).groups
        }
    }
}
