package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLFacetFilters(
    private val groups: MutableList<Group<FilterFacet>> = mutableListOf()
) {

    public operator fun Group<FilterFacet>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    public fun and(block: DSLGroupFacet.() -> Unit) {
        +GroupAnd.Facet(DSLGroupFacet(block))
    }

    public fun or(block: DSLGroupFacet.() -> Unit) {
        +GroupOr.Facet(DSLGroupFacet(block))
    }

    public companion object : DSL<DSLFacetFilters, List<Group<FilterFacet>>> {

        override operator fun invoke(block: DSLFacetFilters.() -> Unit): List<Group<FilterFacet>> {
            return DSLFacetFilters().apply(block).groups.toList()
        }
    }
}