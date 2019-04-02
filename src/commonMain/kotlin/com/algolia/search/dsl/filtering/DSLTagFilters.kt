package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLTagFilters(
    private val groups: MutableList<Group<Filter.Tag>> = mutableListOf()
) {

    public operator fun Group<Filter.Tag>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    public fun and(block: DSLGroupTag.() -> Unit) {
        +Group.And(DSLGroupTag(block))
    }

    public fun or(block: DSLGroupTag.() -> Unit) {
        +Group.Or(DSLGroupTag(block))
    }

    public companion object : DSL<DSLTagFilters, List<Group<Filter.Tag>>> {

        override operator fun invoke(block: DSLTagFilters.() -> Unit): List<Group<Filter.Tag>> {
            return DSLTagFilters().apply(block).groups.toList()
        }
    }
}