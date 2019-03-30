package com.algolia.search.dsl.filter

import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLFilters {

    private val groups = mutableListOf<Group<*>>()

    private fun <T : Filter> put(group: Group<T>) {
        if (group.filters.isNotEmpty()) groups += group
    }

    public fun and(block: DSLGroupFilter.() -> Unit) {
        put(GroupAnd(DSLGroupFilter().apply(block).build()))
    }

    public fun orFacet(block: DSLGroupFacet.() -> Unit) {
        put(GroupOr.Facet(DSLGroupFacet().apply(block).build()))
    }

    public fun orNumeric(block: DSLGroupNumeric.() -> Unit) {
        put(GroupOr.Numeric(DSLGroupNumeric().apply(block).build()))
    }

    public fun orTag(block: DSLGroupTag.() -> Unit) {
        put(GroupOr.Tag(DSLGroupTag().apply(block).build()))
    }

    /**
     * Express every [Group] and [Filter] present in [groups] into a [String].
     * This [String] uses SQL-like syntax, where boolean operators and parenthesis can be used to combined individual
     * filters.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
     */
    public fun build(): String {
        val (andEntries, orEntries) = groups.partition { it is GroupAnd }
        val ands = andEntries.joinToString(separator = " AND ") { group ->
            val condition = andEntries.size > 1 && group.filters.size > 1
            val prefix = if (condition) "(" else ""
            val postfix = if (condition) ")" else ""
            group.filters.joinToString(prefix = prefix, postfix = postfix, separator = " AND ") { it.build() }
        }
        val begin = if (andEntries.isNotEmpty() && orEntries.isNotEmpty()) " AND " else ""
        val ors = orEntries.joinToString(prefix = begin, separator = " AND ") { group ->
            val condition = group.filters.size > 1 && (orEntries.size > 1 || andEntries.isNotEmpty())
            val prefix = if (condition) "(" else ""
            val postfix = if (condition) ")" else ""
            group.filters.joinToString(prefix = prefix, postfix = postfix, separator = " OR ") { it.build() }
        }
        return ands + ors
    }
}