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
        +GroupAnd.All(DSLGroupFilter(block))
    }

    public fun orFacet(block: DSLGroupFacet.() -> Unit) {
        +GroupOr.Facet(DSLGroupFacet(block))
    }

    public fun orNumeric(block: DSLGroupNumeric.() -> Unit) {
        +GroupOr.Numeric(DSLGroupNumeric(block))
    }

    public fun orTag(block: DSLGroupTag.() -> Unit) {
        +GroupOr.Tag(DSLGroupTag(block))
    }

    public companion object : DSL<DSLFilters, List<Group<*>>> {

        override operator fun invoke(block: DSLFilters.() -> Unit): List<Group<*>> {
            return DSLFilters().apply(block).groups.toList()
        }
    }
}