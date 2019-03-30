package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSLParameters


@DSLParameters
public sealed class DSLGroup<T : Filter> {

    private val filters: MutableSet<T> = mutableSetOf()

    public operator fun T.unaryPlus() {
        filters += this
    }

    public operator fun Collection<T>.unaryPlus() {
        filters += this
    }

    public operator fun contains(filter: T): Boolean {
        return filters.contains(filter)
    }

    public fun build(): Set<T> {
        return filters.toSet()
    }
}

public class DSLGroupFacet : DSLGroup<FilterFacet>(), DSLFacet

public class DSLGroupTag : DSLGroup<FilterTag>(), DSLTag

public class DSLGroupNumeric : DSLGroup<FilterNumeric>(), DSLNumeric

public class DSLGroupFilter : DSLGroup<Filter>(), DSLFacet, DSLTag, DSLNumeric