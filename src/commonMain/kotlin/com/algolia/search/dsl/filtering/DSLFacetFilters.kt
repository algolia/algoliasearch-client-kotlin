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

    public fun and(block: DSLGroupFacet.() -> Unit): GroupAnd.Facet {
        return GroupAnd.Facet(DSLGroupFacet(block))
    }

    public fun or(block: DSLGroupFacet.() -> Unit): GroupOr.Facet {
        return GroupOr.Facet(DSLGroupFacet(block))
    }

    public companion object : DSL<DSLFacetFilters, List<Group<Filter.Facet>>> {

        override operator fun invoke(block: DSLFacetFilters.() -> Unit): List<Group<Filter.Facet>> {
            return DSLFacetFilters().apply(block).groups.toList()
        }
    }
}