package com.algolia.search.filter


public enum class NumericOperator(val raw: String) {
    Lesser("<"),
    LesserOrEquals("<="),
    Equals("="),
    NotEquals("!="),
    GreaterOrEquals(">="),
    Greater(">")
}