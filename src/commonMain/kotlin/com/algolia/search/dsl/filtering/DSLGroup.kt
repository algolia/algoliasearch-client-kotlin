package com.algolia.search.dsl.filtering

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.filter.Filter

/**
 * Abstract DSL for building a [Set] [T] [Filter].
 */
@DSLParameters
sealed class DSLGroup<T : Filter> {

    protected abstract val filters: MutableSet<T>

    operator fun T.unaryPlus() {
        filters += this
    }

    operator fun Collection<T>.unaryPlus() {
        filters += this
    }
}

/**
 * DSL for building a [Set] of [Filter.Facet].
 */
class DSLGroupFacet(
    override val filters: MutableSet<Filter.Facet> = mutableSetOf()
) : DSLGroup<Filter.Facet>(), DSLFacet {

    companion object : DSL<DSLGroupFacet, Set<Filter.Facet>> {

        override fun invoke(block: DSLGroupFacet.() -> Unit): Set<Filter.Facet> {
            return DSLGroupFacet().apply(block).filters
        }
    }
}

/**
 * DSL for building a [Set] of [Filter.Tag].
 */
class DSLGroupTag(
    override val filters: MutableSet<Filter.Tag> = mutableSetOf()
) : DSLGroup<Filter.Tag>(), DSLTag {

    companion object : DSL<DSLGroupTag, Set<Filter.Tag>> {

        override fun invoke(block: DSLGroupTag.() -> Unit): Set<Filter.Tag> {
            return DSLGroupTag().apply(block).filters
        }
    }
}

/**
 * DSL for building a [Set] of [Filter.Numeric].
 */
class DSLGroupNumeric(
    override val filters: MutableSet<Filter.Numeric> = mutableSetOf()
) : DSLGroup<Filter.Numeric>(), DSLNumeric {

    companion object : DSL<DSLGroupNumeric, Set<Filter.Numeric>> {

        override fun invoke(block: DSLGroupNumeric.() -> Unit): Set<Filter.Numeric> {
            return DSLGroupNumeric().apply(block).filters
        }
    }
}

/**
 * DSL for building a [Set] of [Filter].
 */
class DSLGroupFilter(
    override val filters: MutableSet<Filter> = mutableSetOf()
) : DSLGroup<Filter>(), DSLFacet, DSLTag, DSLNumeric {

    override fun Filter.Facet.unaryPlus() {
        filters += this
    }

    override fun Filter.Tag.unaryPlus() {
        filters += this
    }

    override fun Filter.Numeric.unaryPlus() {
        filters += this
    }

    companion object : DSL<DSLGroupFilter, Set<Filter>> {

        override fun invoke(block: DSLGroupFilter.() -> Unit): Set<Filter> {
            return DSLGroupFilter().apply(block).filters
        }
    }
}
