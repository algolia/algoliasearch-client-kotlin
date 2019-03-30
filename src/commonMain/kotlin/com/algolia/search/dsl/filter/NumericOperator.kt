package com.algolia.search.dsl.filter


public enum class NumericOperator(val raw: String) {
    Lesser("<"),
    LesserOrEquals("<="),
    Equals("="),
    NotEquals("!="),
    GreaterOrEquals(">="),
    Greater(">")
}