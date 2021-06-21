package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup

/**
 * DSL for building a [List] of [FilterGroup] of [Filter.Facet].
 */
@DSLParameters
public class DSLFilters(
    private val groups: MutableSet<FilterGroup<*>> = mutableSetOf()
) {

    /**
     * Add [this] to [groups] if it is not empty.
     */
    public operator fun FilterGroup<*>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    /**
     * Create and add a [FilterGroup.And.Any] which can contain any [Filter] to [groups].
     */
    public fun and(block: DSLGroupFilter.() -> Unit) {
        +FilterGroup.And.Any(DSLGroupFilter(block))
    }

    /**
     * Create and add a [FilterGroup.Or.Facet] which can only contain [Filter.Facet] to [groups].
     */
    public fun orFacet(block: DSLGroupFacet.() -> Unit) {
        +FilterGroup.Or.Facet(DSLGroupFacet(block))
    }

    /**
     * Create and add a [FilterGroup.Or.Numeric] which can only contain [Filter.Numeric] to [groups].
     */
    public fun orNumeric(block: DSLGroupNumeric.() -> Unit) {
        +FilterGroup.Or.Numeric(DSLGroupNumeric(block))
    }

    /**
     * Create and add a [FilterGroup.Or.Tag] which can only contain [Filter.Tag] to [groups].
     */
    public fun orTag(block: DSLGroupTag.() -> Unit) {
        +FilterGroup.Or.Tag(DSLGroupTag(block))
    }

    public companion object : DSL<DSLFilters, Set<FilterGroup<*>>> {

        override operator fun invoke(block: DSLFilters.() -> Unit): Set<FilterGroup<*>> {
            return DSLFilters().apply(block).groups
        }
    }
}
