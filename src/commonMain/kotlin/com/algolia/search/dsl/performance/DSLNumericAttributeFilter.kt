package com.algolia.search.dsl.performance

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.NumericAttributeFilter

/**
 * DSL for building a [List] of [NumericAttributeFilter].
 */
@DSLParameters
public class DSLNumericAttributeFilter(
    private val numericAttributeFilters: MutableList<NumericAttributeFilter> = mutableListOf()
) {

    /**
     * Convenience method.
     */
    public operator fun String.invoke(boolean: Boolean): NumericAttributeFilter {
        return Attribute(this).invoke(boolean)
    }

    /**
     * Create a [NumericAttributeFilter] with [this] and [boolean].
     */
    public operator fun Attribute.invoke(boolean: Boolean): NumericAttributeFilter {
        return NumericAttributeFilter(this, boolean)
    }

    /**
     * Convenience method.
     */
    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    /**
     * Convenience method.
     */
    public operator fun Attribute.unaryPlus() {
        +NumericAttributeFilter(this)
    }

    /**
     * Add [this] to [numericAttributeFilters].
     */
    public operator fun NumericAttributeFilter.unaryPlus() {
        numericAttributeFilters += this
    }

    public companion object : DSL<DSLNumericAttributeFilter, List<NumericAttributeFilter>> {

        override operator fun invoke(block: DSLNumericAttributeFilter.() -> Unit): List<NumericAttributeFilter> {
            return DSLNumericAttributeFilter().apply(block).numericAttributeFilters
        }
    }
}
