package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLTagFilters(
    private val groups: MutableList<Group<FilterTag>> = mutableListOf()
) {

    private fun put(group: Group<FilterTag>) {
        if (group.filters.isNotEmpty()) groups += group
    }

    public fun and(block: DSLGroupTag.() -> Unit) {
        put(GroupAnd.Tag(DSLGroupTag().apply(block).build()))
    }

    public fun or(block: DSLGroupTag.() -> Unit) {
        put(GroupOr.Tag(DSLGroupTag().apply(block).build()))
    }

    public fun build(): List<List<String>> {
        val (andEntries, orEntries) = groups.partition { it is GroupAnd.Tag }
        val ands = andEntries.flatMap { group -> group.filters.map { listOf(it.expression) } }
        val ors = orEntries.map { group -> group.filters.map { it.expression } }

        return ands + ors
    }
}