package com.algolia.search.dsl.filtering

import com.algolia.search.model.Attribute
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.NumericOperator

/**
 * DSL for building a [Filter.Numeric].
 * [Filter by numeric][https://www.algolia.com/doc/guides/managing-results/refine-results/filtering/how-to/filter-by-numeric-value/]
 */
@Suppress("PropertyName")
interface DSLNumeric {

    val Less get() = NumericOperator.Less
    val LessOrEquals get() = NumericOperator.LessOrEquals
    val NotEquals get() = NumericOperator.NotEquals
    val Equals get() = NumericOperator.Equals
    val Greater get() = NumericOperator.Greater
    val GreaterOrEquals get() = NumericOperator.GreaterOrEquals

    operator fun Filter.Numeric.unaryPlus()

    /**
     * Convenience method.
     */
    fun range(name: String, range: IntRange, isNegated: Boolean = false) {
        range(Attribute(name), range, isNegated)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute] and a [range] of [IntRange].
     */
    fun range(attribute: Attribute, range: IntRange, isNegated: Boolean = false) {
        +Filter.Numeric(attribute, range, isNegated)
    }

    /**
     * Convenience method.
     */
    fun range(name: String, range: LongRange, isNegated: Boolean = false) {
        range(Attribute(name), range, isNegated)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute] and a [range] of [LongRange].
     */
    fun range(attribute: Attribute, range: LongRange, isNegated: Boolean = false) {
        +Filter.Numeric(attribute, range, isNegated)
    }

    /**
     * Convenience method.
     */
    fun range(name: String, lowerBound: Float, upperBound: Float, isNegated: Boolean = false) {
        range(Attribute(name), lowerBound, upperBound, isNegated)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute] and a [lowerBound] and [upperBound] of [Float].
     */
    fun range(attribute: Attribute, lowerBound: Float, upperBound: Float, isNegated: Boolean = false) {
        +Filter.Numeric(attribute, lowerBound, upperBound, isNegated)
    }

    /**
     * Convenience method.
     */
    fun range(name: String, lowerBound: Double, upperBound: Double, isNegated: Boolean = false) {
        range(Attribute(name), lowerBound, upperBound, isNegated)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute] and a [lowerBound] and [upperBound] of [Double].
     */
    fun range(attribute: Attribute, lowerBound: Double, upperBound: Double, isNegated: Boolean = false) {
        +Filter.Numeric(attribute, lowerBound, upperBound, isNegated)
    }

    /**
     * Convenience method.
     */
    fun comparison(name: String, operator: NumericOperator, value: Number, isNegated: Boolean = false) {
        comparison(Attribute(name), operator, value, isNegated)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute], an [operator] and a [value].
     */
    fun comparison(attribute: Attribute, operator: NumericOperator, value: Number, isNegated: Boolean = false) {
        +Filter.Numeric(attribute, operator, value, isNegated)
    }
}
