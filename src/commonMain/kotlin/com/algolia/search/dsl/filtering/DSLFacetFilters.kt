package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLFacetFilters(
    private val groups: MutableList<Group<Filter.Facet>> = mutableListOf()
) {

    public operator fun Group<Filter.Facet>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    public fun and(block: DSLGroupFacet.() -> Unit) {
        +Group.And(DSLGroupFacet(block))
    }

    public fun or(block: DSLGroupFacet.() -> Unit) {
        +Group.Or(DSLGroupFacet(block))
    }

    public companion object : DSL<DSLFacetFilters, List<Group<Filter.Facet>>> {

        override operator fun invoke(block: DSLFacetFilters.() -> Unit): List<Group<Filter.Facet>> {
            return DSLFacetFilters().apply(block).groups.toList()
        }
    }
}