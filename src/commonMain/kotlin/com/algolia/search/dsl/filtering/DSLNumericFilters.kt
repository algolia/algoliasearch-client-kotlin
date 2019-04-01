package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters


@DSLParameters
public class DSLNumericFilters(
    private val groups: MutableList<Group<FilterNumeric>> = mutableListOf()
) {

    public operator fun Group<FilterNumeric>.unaryPlus() {
        if (isNotEmpty()) groups += this
    }

    public fun and(block: DSLGroupNumeric.() -> Unit) {
        +GroupAnd.Numeric(DSLGroupNumeric(block))
    }

    public fun or(block: DSLGroupNumeric.() -> Unit) {
        +GroupOr.Numeric(DSLGroupNumeric(block))
    }

    public companion object : DSL<DSLNumericFilters, List<Group<FilterNumeric>>> {

        override operator fun invoke(block: DSLNumericFilters.() -> Unit): List<Group<FilterNumeric>> {
            return DSLNumericFilters().apply(block).groups.toList()
        }
    }
}