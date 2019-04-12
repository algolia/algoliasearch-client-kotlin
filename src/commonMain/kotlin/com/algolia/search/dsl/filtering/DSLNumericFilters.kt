package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup


@DSLParameters
public class DSLNumericFilters(
    private val groups: MutableList<FilterGroup<Filter.Numeric>> = mutableListOf()
) {

    public operator fun FilterGroup<Filter.Numeric>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    public fun and(block: DSLGroupNumeric.() -> Unit) {
        +FilterGroup.And.Numeric(DSLGroupNumeric(block))
    }

    public fun or(block: DSLGroupNumeric.() -> Unit) {
        +FilterGroup.Or.Numeric(DSLGroupNumeric(block))
    }

    public companion object : DSL<DSLNumericFilters, List<FilterGroup<Filter.Numeric>>> {

        override operator fun invoke(block: DSLNumericFilters.() -> Unit): List<FilterGroup<Filter.Numeric>> {
            return DSLNumericFilters().apply(block).groups.toList()
        }
    }
}