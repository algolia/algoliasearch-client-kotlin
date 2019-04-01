package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLFacetFilters(
    private val groups: MutableList<Group<FilterFacet>> = mutableListOf()
) {

    private fun put(group: Group<FilterFacet>) {
        if (group.isNotEmpty()) groups += group
    }

    public fun and(block: DSLGroupFacet.() -> Unit) {
        put(GroupAnd.Facet(DSLGroupFacet().apply(block).filters))
    }

    public fun or(block: DSLGroupFacet.() -> Unit) {
        put(GroupOr.Facet(DSLGroupFacet().apply(block).filters))
    }

    public fun build(): List<List<String>> {
        val (andEntries, orEntries) = groups.partition { it is GroupAnd.Facet }
        val ands = andEntries.flatMap { group -> group.map { listOf(it.expression) } }
        val ors = orEntries.map { group -> group.map { it.expression } }

        return ands + ors
    }
}