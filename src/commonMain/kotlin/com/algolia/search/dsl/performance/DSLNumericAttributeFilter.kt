package com.algolia.search.dsl.performance

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.NumericAttributeFilter


@DSLParameters
public class DSLNumericAttributeFilter(
    private val numericAttributeFilters: MutableList<NumericAttributeFilter> = mutableListOf()
) {

    public infix fun String.equalOnly(boolean: Boolean): NumericAttributeFilter {
        return Attribute(this) equalOnly boolean
    }

    public infix fun Attribute.equalOnly(boolean: Boolean): NumericAttributeFilter {
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

    public companion object : DSL<DSLNumericAttributeFilter, List<NumericAttributeFilter>> {

        override operator fun invoke(block: DSLNumericAttributeFilter.() -> Unit): List<NumericAttributeFilter> {
            return DSLNumericAttributeFilter().apply(block).numericAttributeFilters
        }
    }
}