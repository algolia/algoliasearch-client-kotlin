package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.filter.FilterGroup


@DSLParameters
public class DSLFilters(
    private val groups: MutableList<FilterGroup<*>> = mutableListOf()
) {

    public operator fun FilterGroup<*>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    public fun and(block: DSLGroupFilter.() -> Unit) {
        +FilterGroup.And(DSLGroupFilter(block))
    }

    public fun orFacet(block: DSLGroupFacet.() -> Unit) {
        +FilterGroup.Or(DSLGroupFacet(block))
    }

    public fun orNumeric(block: DSLGroupNumeric.() -> Unit) {
        +FilterGroup.Or(DSLGroupNumeric(block))
    }

    public fun orTag(block: DSLGroupTag.() -> Unit) {
        +FilterGroup.Or(DSLGroupTag(block))
    }

    public companion object : DSL<DSLFilters, List<FilterGroup<*>>> {

        override operator fun invoke(block: DSLFilters.() -> Unit): List<FilterGroup<*>> {
            return DSLFilters().apply(block).groups.toList()
        }
    }
}