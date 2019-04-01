package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters


@DSLParameters
public sealed class DSLGroup<T : Filter> {

    protected abstract val filters: MutableSet<T>

    public operator fun T.unaryPlus() {
        filters += this
    }

    public operator fun Collection<T>.unaryPlus() {
        filters += this
    }
}

public class DSLGroupFacet(
    override val filters: MutableSet<FilterFacet> = mutableSetOf()
) : DSLGroup<FilterFacet>(), DSLFacet {

    public companion object : DSL<DSLGroupFacet, MutableSet<FilterFacet>> {

        override fun invoke(block: DSLGroupFacet.() -> Unit): MutableSet<FilterFacet> {
            return DSLGroupFacet().apply(block).filters
        }
    }
}

public class DSLGroupTag(
    override val filters: MutableSet<FilterTag> = mutableSetOf()
) : DSLGroup<FilterTag>(), DSLTag {

    public companion object : DSL<DSLGroupTag, MutableSet<FilterTag>> {

        override fun invoke(block: DSLGroupTag.() -> Unit): MutableSet<FilterTag> {
            return DSLGroupTag().apply(block).filters
        }
    }
}

public class DSLGroupNumeric(
    override val filters: MutableSet<FilterNumeric> = mutableSetOf()
) : DSLGroup<FilterNumeric>(), DSLNumeric {

    public companion object : DSL<DSLGroupNumeric, MutableSet<FilterNumeric>> {

        override fun invoke(block: DSLGroupNumeric.() -> Unit): MutableSet<FilterNumeric> {
            return DSLGroupNumeric().apply(block).filters
        }
    }
}

public class DSLGroupFilter(
    override val filters: MutableSet<Filter> = mutableSetOf()
) : DSLGroup<Filter>(), DSLFacet, DSLTag, DSLNumeric {

    public companion object : DSL<DSLGroupFilter, MutableSet<Filter>> {

        override fun invoke(block: DSLGroupFilter.() -> Unit): MutableSet<Filter> {
            return DSLGroupFilter().apply(block).filters
        }
    }
}