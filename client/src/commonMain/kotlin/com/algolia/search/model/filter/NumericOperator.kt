package com.algolia.search.model.filter

/**
 * Operator to be used with [Filter.Numeric.Value.Comparison].
 */
public enum class NumericOperator(public val raw: String) {
    Less("<"),
    LessOrEquals("<="),
    Equals("="),
    NotEquals("!="),
    GreaterOrEquals(">="),
    Greater(">")
}
