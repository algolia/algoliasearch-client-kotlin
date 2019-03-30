package com.algolia.search.dsl.filter

import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLOptionalFilters {

    private val groups = mutableListOf<Group<FilterFacet>>()

    private fun put(group: Group<FilterFacet>) {
        if (group.filters.isNotEmpty()) groups += group
    }

    public fun and(block: DSLGroupFacet.() -> Unit) {
        put(GroupAndFacet(DSLGroupFacet().apply(block).build()))
    }

    public fun or(block: DSLGroupFacet.() -> Unit) {
        put(GroupOr.Facet(DSLGroupFacet().apply(block).build()))
    }

    /**
     * Express every [Group] and [Filter] present in [groups] into a nested list of [String].
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/]
     */
    public fun build(): List<List<String>> {
        val (andEntries, orEntries) = groups.partition { it is GroupAndFacet }
        val ands = andEntries.flatMap { group -> group.filters.map { listOf(it.expression) } }
        val ors = orEntries.map { group -> group.filters.map { it.expression } }

        return ands + ors
    }
}