package com.algolia.search.dsl.filtering

import com.algolia.search.model.Attribute
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.NumericOperator


@Suppress("PropertyName")
public interface DSLNumeric {

    val Lesser get() = NumericOperator.Lesser
    val LesserOrEquals get() = NumericOperator.LesserOrEquals
    val NotEquals get() = NumericOperator.NotEquals
    val Equals get() = NumericOperator.Equals
    val Greater get() = NumericOperator.Greater
    val GreaterOrEquals get() = NumericOperator.GreaterOrEquals

    fun range(name: String, range: IntRange): Filter.Numeric {
        return range(Attribute(name), range)
    }

    fun range(attribute: Attribute, range: IntRange): Filter.Numeric {
        return Filter.Numeric(attribute, range)
    }

    fun range(name: String, range: LongRange): Filter.Numeric {
        return range(Attribute(name), range)
    }

    fun range(attribute: Attribute, range: LongRange): Filter.Numeric {
        return Filter.Numeric(attribute, range)
    }

    fun range(name: String, lowerBound: Float, upperBound: Float): Filter.Numeric {
        return range(Attribute(name), lowerBound, upperBound)
    }

    fun range(attribute: Attribute, lowerBound: Float, upperBound: Float): Filter.Numeric {
        return Filter.Numeric(attribute, lowerBound, upperBound)
    }

    fun range(name: String, lowerBound: Double, upperBound: Double): Filter.Numeric {
        return range(Attribute(name), lowerBound, upperBound)
    }

    fun range(attribute: Attribute, lowerBound: Double, upperBound: Double): Filter.Numeric {
        return Filter.Numeric(attribute, lowerBound, upperBound)
    }

    fun comparison(name: String, operator: NumericOperator, value: Number): Filter.Numeric {
        return comparison(Attribute(name), operator, value)
    }

    fun comparison(attribute: Attribute, operator: NumericOperator, value: Number): Filter.Numeric {
        return Filter.Numeric(attribute, operator, value)
    }
}