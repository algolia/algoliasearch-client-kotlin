package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup


@DSLParameters
public class DSLFacetFilters(
    private val groups: MutableList<FilterGroup<Filter.Facet>> = mutableListOf()
) {

    public operator fun FilterGroup<Filter.Facet>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    public fun and(block: DSLGroupFacet.() -> Unit) {
        +FilterGroup.And(DSLGroupFacet(block))
    }

    public fun or(block: DSLGroupFacet.() -> Unit) {
        +FilterGroup.Or(DSLGroupFacet(block))
    }

    public companion object : DSL<DSLFacetFilters, List<FilterGroup<Filter.Facet>>> {

        override operator fun invoke(block: DSLFacetFilters.() -> Unit): List<FilterGroup<Filter.Facet>> {
            return DSLFacetFilters().apply(block).groups.toList()
        }
    }
}