package com.algolia.search.dsl.performance

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.NumericAttributeFilter

/**
 * DSL for building a [List] of [NumericAttributeFilter].
 */
@DSLParameters
class DSLNumericAttributeFilter(
    private val numericAttributeFilters: MutableList<NumericAttributeFilter> = mutableListOf()
) {

    /**
     * Convenience method.
     */
    operator fun String.invoke(boolean: Boolean): NumericAttributeFilter {
        return Attribute(this).invoke(boolean)
    }

    /**
     * Create a [NumericAttributeFilter] with [this] and [boolean].
     */
    operator fun Attribute.invoke(boolean: Boolean): NumericAttributeFilter {
        return NumericAttributeFilter(this, boolean)
    }

    /**
     * Convenience method.
     */
    operator fun String.unaryPlus() {
        +Attribute(this)
    }

    /**
     * Convenience method.
     */
    operator fun Attribute.unaryPlus() {
        +NumericAttributeFilter(this)
    }

    /**
     * Add [this] to [numericAttributeFilters].
     */
    operator fun NumericAttributeFilter.unaryPlus() {
        numericAttributeFilters += this
    }

    companion object : DSL<DSLNumericAttributeFilter, List<NumericAttributeFilter>> {

        override operator fun invoke(block: DSLNumericAttributeFilter.() -> Unit): List<NumericAttributeFilter> {
            return DSLNumericAttributeFilter().apply(block).numericAttributeFilters
        }
    }
}
