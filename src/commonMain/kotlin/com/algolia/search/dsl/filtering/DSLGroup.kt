package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSLParameters


@DSLParameters
public sealed class DSLGroup<T : Filter> {

    abstract val filters: MutableSet<T>

    public operator fun T.unaryPlus() {
        filters += this
    }

    public operator fun Collection<T>.unaryPlus() {
        filters += this
    }

    public operator fun contains(filter: T): Boolean {
        return filters.contains(filter)
    }
}

public class DSLGroupFacet(
    override val filters: MutableSet<FilterFacet> = mutableSetOf()
) : DSLGroup<FilterFacet>(), DSLFacet

public class DSLGroupTag(
    override val filters: MutableSet<FilterTag> = mutableSetOf()
) : DSLGroup<FilterTag>(), DSLTag

public class DSLGroupNumeric(
    override val filters: MutableSet<FilterNumeric> = mutableSetOf()
) : DSLGroup<FilterNumeric>(), DSLNumeric

public class DSLGroupFilter(
    override val filters: MutableSet<Filter> = mutableSetOf()
) : DSLGroup<Filter>(), DSLFacet, DSLTag, DSLNumeric