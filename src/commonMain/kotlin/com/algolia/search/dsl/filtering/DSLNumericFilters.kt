package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLNumericFilters(
    private val groups: MutableList<Group<FilterNumeric>> = mutableListOf()
) {

    private fun put(group: Group<FilterNumeric>) {
        if (group.filters.isNotEmpty()) groups += group
    }

    public fun and(block: DSLGroupNumeric.() -> Unit) {
        put(GroupAnd.Numeric(DSLGroupNumeric().apply(block).build()))
    }

    public fun or(block: DSLGroupNumeric.() -> Unit) {
        put(GroupOr.Numeric(DSLGroupNumeric().apply(block).build()))
    }

    public fun build(): List<List<String>> {
        val (andEntries, orEntries) = groups.partition { it is GroupAnd.Numeric }
        val ands = andEntries.flatMap { group -> group.filters.map { listOf(it.expression) } }
        val ors = orEntries.map { group -> group.filters.map { it.expression } }

        return ands + ors
    }
}