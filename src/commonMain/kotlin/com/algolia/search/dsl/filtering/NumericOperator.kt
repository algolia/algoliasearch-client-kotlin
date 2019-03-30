package com.algolia.search.dsl.filtering


public enum class NumericOperator(val raw: String) {
    Lesser("<"),
    LesserOrEquals("<="),
    Equals("="),
    NotEquals("!="),
    GreaterOrEquals(">="),
    Greater(">")
}