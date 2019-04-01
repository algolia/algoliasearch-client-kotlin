package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLTagFilters(
    private val groups: MutableList<Group<FilterTag>> = mutableListOf()
) {

    private fun put(group: Group<FilterTag>) {
        if (group.isNotEmpty()) groups += group
    }

    public fun and(block: DSLGroupTag.() -> Unit) {
        put(GroupAnd.Tag(DSLGroupTag().apply(block).filters))
    }

    public fun or(block: DSLGroupTag.() -> Unit) {
        put(GroupOr.Tag(DSLGroupTag().apply(block).filters))
    }

    public fun build(): List<List<String>> {
        val (andEntries, orEntries) = groups.partition { it is GroupAnd.Tag }
        val ands = andEntries.flatMap { group -> group.map { listOf(it.expression) } }
        val ors = orEntries.map { group -> group.map { it.expression } }

        return ands + ors
    }
}