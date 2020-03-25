package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup

/**
 * DSL for building a [List] of [FilterGroup] of [Filter.Facet].
 */
@DSLParameters
class DSLFacetFilters(
    private val groups: MutableSet<FilterGroup<Filter.Facet>> = mutableSetOf()
) {

    /**
     * Add [this] to [groups] if it is not empty.
     */
    operator fun FilterGroup<Filter.Facet>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    /**
     * Create and add a [FilterGroup.And] which can only contain [Filter.Facet] to [groups].
     */
    fun and(block: DSLGroupFacet.() -> Unit) {
        +FilterGroup.And.Facet(DSLGroupFacet(block))
    }

    /**
     * Create and add a [FilterGroup.Or] which can only contain [Filter.Facet] to [groups].
     */
    fun or(block: DSLGroupFacet.() -> Unit) {
        +FilterGroup.Or.Facet(DSLGroupFacet(block))
    }

    companion object : DSL<DSLFacetFilters, Set<FilterGroup<Filter.Facet>>> {

        override operator fun invoke(block: DSLFacetFilters.() -> Unit): Set<FilterGroup<Filter.Facet>> {
            return DSLFacetFilters().apply(block).groups
        }
    }
}
