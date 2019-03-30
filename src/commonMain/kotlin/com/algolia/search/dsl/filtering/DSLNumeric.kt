package com.algolia.search.dsl.filtering

import com.algolia.search.model.Attribute


@Suppress("PropertyName")
public interface DSLNumeric {

    val Lesser get() = NumericOperator.Lesser
    val LesserOrEquals get() = NumericOperator.LesserOrEquals
    val NotEquals get() = NumericOperator.NotEquals
    val Equals get() = NumericOperator.Equals
    val Greater get() = NumericOperator.Greater
    val GreaterOrEquals get() = NumericOperator.GreaterOrEquals

    fun range(name: String, range: IntRange): FilterRange {
        return range(Attribute(name), range)
    }

    fun range(attribute: Attribute, range: IntRange): FilterRange {
        return FilterRange(attribute, range)
    }

    fun range(name: String, range: LongRange): FilterRange {
        return range(Attribute(name), range)
    }

    fun range(attribute: Attribute, range: LongRange): FilterRange {
        return FilterRange(attribute, range)
    }

    fun range(name: String, lowerBound: Float, upperBound: Float): FilterRange {
        return range(Attribute(name), lowerBound, upperBound)
    }

    fun range(attribute: Attribute, lowerBound: Float, upperBound: Float): FilterRange {
        return FilterRange(attribute, lowerBound, upperBound)
    }

    fun range(name: String, lowerBound: Double, upperBound: Double): FilterRange {
        return range(Attribute(name), lowerBound, upperBound)
    }

    fun range(attribute: Attribute, lowerBound: Double, upperBound: Double): FilterRange {
        return FilterRange(attribute, lowerBound, upperBound)
    }

    fun comparison(name: String, operator: NumericOperator, value: Number): FilterComparison {
        return comparison(Attribute(name), operator, value)
    }

    fun comparison(attribute: Attribute, operator: NumericOperator, value: Number): FilterComparison {
        return FilterComparison(attribute, operator, value)
    }
}