package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup


@DSLParameters
public class DSLTagFilters(
    private val groups: MutableList<FilterGroup<Filter.Tag>> = mutableListOf()
) {

    public operator fun FilterGroup<Filter.Tag>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    public fun and(block: DSLGroupTag.() -> Unit) {
        +FilterGroup.And(DSLGroupTag(block))
    }

    public fun or(block: DSLGroupTag.() -> Unit) {
        +FilterGroup.Or(DSLGroupTag(block))
    }

    public companion object : DSL<DSLTagFilters, List<FilterGroup<Filter.Tag>>> {

        override operator fun invoke(block: DSLTagFilters.() -> Unit): List<FilterGroup<Filter.Tag>> {
            return DSLTagFilters().apply(block).groups.toList()
        }
    }
}