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
    override val filters: MutableSet<Filter.Facet> = mutableSetOf()
) : DSLGroup<Filter.Facet>(), DSLFacet {

    public companion object : DSL<DSLGroupFacet, MutableSet<Filter.Facet>> {

        override fun invoke(block: DSLGroupFacet.() -> Unit): MutableSet<Filter.Facet> {
            return DSLGroupFacet().apply(block).filters
        }
    }
}

public class DSLGroupTag(
    override val filters: MutableSet<Filter.Tag> = mutableSetOf()
) : DSLGroup<Filter.Tag>(), DSLTag {

    public companion object : DSL<DSLGroupTag, MutableSet<Filter.Tag>> {

        override fun invoke(block: DSLGroupTag.() -> Unit): MutableSet<Filter.Tag> {
            return DSLGroupTag().apply(block).filters
        }
    }
}

public class DSLGroupNumeric(
    override val filters: MutableSet<Filter.Numeric> = mutableSetOf()
) : DSLGroup<Filter.Numeric>(), DSLNumeric {

    public companion object : DSL<DSLGroupNumeric, MutableSet<Filter.Numeric>> {

        override fun invoke(block: DSLGroupNumeric.() -> Unit): MutableSet<Filter.Numeric> {
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