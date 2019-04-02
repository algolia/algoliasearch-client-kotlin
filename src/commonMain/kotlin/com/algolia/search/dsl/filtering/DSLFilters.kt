package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLFilters(
    private val groups: MutableList<Group<*>> = mutableListOf()
) {

    public operator fun Group<*>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    public fun and(block: DSLGroupFilter.() -> Unit) {
        +Group.And(DSLGroupFilter(block))
    }

    public fun orFacet(block: DSLGroupFacet.() -> Unit) {
        +Group.Or(DSLGroupFacet(block))
    }

    public fun orNumeric(block: DSLGroupNumeric.() -> Unit) {
        +Group.Or(DSLGroupNumeric(block))
    }

    public fun orTag(block: DSLGroupTag.() -> Unit) {
        +Group.Or(DSLGroupTag(block))
    }

    public companion object : DSL<DSLFilters, List<Group<*>>> {

        override operator fun invoke(block: DSLFilters.() -> Unit): List<Group<*>> {
            return DSLFilters().apply(block).groups.toList()
        }
    }
}