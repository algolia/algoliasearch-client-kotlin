package com.algolia.search.dsl.performance

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.NumericAttributeFilter


@DSLParameters
public class DSLNumericAttributeFilter(
    private val numericAttributeFilters: MutableList<NumericAttributeFilter> = mutableListOf()
) {

    public infix fun String.equalsOnly(boolean: Boolean): NumericAttributeFilter {
        return Attribute(this) equalsOnly boolean
    }

    public infix fun Attribute.equalsOnly(boolean: Boolean): NumericAttributeFilter {
        return NumericAttributeFilter(this, boolean)
    }

    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    public operator fun Attribute.unaryPlus() {
        +NumericAttributeFilter(this)
    }

    public operator fun NumericAttributeFilter.unaryPlus() {
        numericAttributeFilters += this
    }

    public fun build(): List<NumericAttributeFilter> {
        return numericAttributeFilters.toList()
    }
}