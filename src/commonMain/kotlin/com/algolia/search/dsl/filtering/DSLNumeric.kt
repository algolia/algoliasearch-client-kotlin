package com.algolia.search.dsl.filtering

import com.algolia.search.model.Attribute
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.NumericOperator

/**
 * DSL for building a [Filter.Numeric].
 * [Filter by numeric][https://www.algolia.com/doc/guides/managing-results/refine-results/filtering/how-to/filter-by-numeric-value/]
 */
@Suppress("PropertyName")
public interface DSLNumeric {

    public val Less: NumericOperator get() = NumericOperator.Less
    public val LessOrEquals: NumericOperator get() = NumericOperator.LessOrEquals
    public val NotEquals: NumericOperator get() = NumericOperator.NotEquals
    public val Equals: NumericOperator get() = NumericOperator.Equals
    public val Greater: NumericOperator get() = NumericOperator.Greater
    public val GreaterOrEquals: NumericOperator get() = NumericOperator.GreaterOrEquals

    public operator fun Filter.Numeric.unaryPlus()

    /**
     * Convenience method.
     */
    public fun range(name: String, range: IntRange, isNegated: Boolean = false) {
        range(Attribute(name), range, isNegated)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute] and a [range] of [IntRange].
     */
    public fun range(attribute: Attribute, range: IntRange, isNegated: Boolean = false) {
        +Filter.Numeric(attribute, range, isNegated)
    }

    /**
     * Convenience method.
     */
    public fun range(name: String, range: LongRange, isNegated: Boolean = false) {
        range(Attribute(name), range, isNegated)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute] and a [range] of [LongRange].
     */
    public fun range(attribute: Attribute, range: LongRange, isNegated: Boolean = false) {
        +Filter.Numeric(attribute, range, isNegated)
    }

    /**
     * Convenience method.
     */
    public fun range(name: String, lowerBound: Float, upperBound: Float, isNegated: Boolean = false) {
        range(Attribute(name), lowerBound, upperBound, isNegated)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute] and a [lowerBound] and [upperBound] of [Float].
     */
    public fun range(attribute: Attribute, lowerBound: Float, upperBound: Float, isNegated: Boolean = false) {
        +Filter.Numeric(attribute, lowerBound, upperBound, isNegated)
    }

    /**
     * Convenience method.
     */
    public fun range(name: String, lowerBound: Double, upperBound: Double, isNegated: Boolean = false) {
        range(Attribute(name), lowerBound, upperBound, isNegated)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute] and a [lowerBound] and [upperBound] of [Double].
     */
    public fun range(attribute: Attribute, lowerBound: Double, upperBound: Double, isNegated: Boolean = false) {
        +Filter.Numeric(attribute, lowerBound, upperBound, isNegated)
    }

    /**
     * Convenience method.
     */
    public fun comparison(name: String, operator: NumericOperator, value: Number, isNegated: Boolean = false) {
        comparison(Attribute(name), operator, value, isNegated)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute], an [operator] and a [value].
     */
    public fun comparison(attribute: Attribute, operator: NumericOperator, value: Number, isNegated: Boolean = false) {
        +Filter.Numeric(attribute, operator, value, isNegated)
    }
}
