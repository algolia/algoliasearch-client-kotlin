package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLNumericFilters(
    private val groups: MutableList<Group<Filter.Numeric>> = mutableListOf()
) {

    public operator fun Group<Filter.Numeric>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    public fun and(block: DSLGroupNumeric.() -> Unit) {
        +GroupAnd.Numeric(DSLGroupNumeric(block))
    }

    public fun or(block: DSLGroupNumeric.() -> Unit) {
        +GroupOr.Numeric(DSLGroupNumeric(block))
    }

    public companion object : DSL<DSLNumericFilters, List<Group<Filter.Numeric>>> {

        override operator fun invoke(block: DSLNumericFilters.() -> Unit): List<Group<Filter.Numeric>> {
            return DSLNumericFilters().apply(block).groups.toList()
        }
    }
}