package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLTagFilters(
    private val groups: MutableList<Group<FilterTag>> = mutableListOf()
) {

    public operator fun Group<FilterTag>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    public fun and(block: DSLGroupTag.() -> Unit) {
        +GroupAnd.Tag(DSLGroupTag(block))
    }

    public fun or(block: DSLGroupTag.() -> Unit) {
        +GroupOr.Tag(DSLGroupTag(block))
    }

    public companion object : DSL<DSLTagFilters, List<Group<FilterTag>>> {

        override operator fun invoke(block: DSLTagFilters.() -> Unit): List<Group<FilterTag>> {
            return DSLTagFilters().apply(block).groups.toList()
        }
    }
}