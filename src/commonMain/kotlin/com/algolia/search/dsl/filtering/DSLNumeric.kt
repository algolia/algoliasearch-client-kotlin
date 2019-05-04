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

    val Less get() = NumericOperator.Less
    val LessOrEquals get() = NumericOperator.LessOrEquals
    val NotEquals get() = NumericOperator.NotEquals
    val Equals get() = NumericOperator.Equals
    val Greater get() = NumericOperator.Greater
    val GreaterOrEquals get() = NumericOperator.GreaterOrEquals

    /**
     * Convenience method.
     */
    fun range(name: String, range: IntRange): Filter.Numeric {
        return range(Attribute(name), range)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute] and a [range] of [IntRange].
     */
    fun range(attribute: Attribute, range: IntRange): Filter.Numeric {
        return Filter.Numeric(attribute, range)
    }

    /**
     * Convenience method.
     */
    fun range(name: String, range: LongRange): Filter.Numeric {
        return range(Attribute(name), range)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute] and a [range] of [LongRange].
     */
    fun range(attribute: Attribute, range: LongRange): Filter.Numeric {
        return Filter.Numeric(attribute, range)
    }

    /**
     * Convenience method.
     */
    fun range(name: String, lowerBound: Float, upperBound: Float): Filter.Numeric {
        return range(Attribute(name), lowerBound, upperBound)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute] and a [lowerBound] and [upperBound] of [Float].
     */
    fun range(attribute: Attribute, lowerBound: Float, upperBound: Float): Filter.Numeric {
        return Filter.Numeric(attribute, lowerBound, upperBound)
    }

    /**
     * Convenience method.
     */
    fun range(name: String, lowerBound: Double, upperBound: Double): Filter.Numeric {
        return range(Attribute(name), lowerBound, upperBound)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute] and a [lowerBound] and [upperBound] of [Double].
     */
    fun range(attribute: Attribute, lowerBound: Double, upperBound: Double): Filter.Numeric {
        return Filter.Numeric(attribute, lowerBound, upperBound)
    }

    /**
     * Convenience method.
     */
    fun comparison(name: String, operator: NumericOperator, value: Number): Filter.Numeric {
        return comparison(Attribute(name), operator, value)
    }

    /**
     * Create a [Filter.Numeric] with an [attribute], an [operator] and a [value].
     */
    fun comparison(attribute: Attribute, operator: NumericOperator, value: Number): Filter.Numeric {
        return Filter.Numeric(attribute, operator, value)
    }
}