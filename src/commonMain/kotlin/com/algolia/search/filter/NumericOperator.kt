package com.algolia.search.filter


public enum class NumericOperator(val raw: String) {
    Lesser("<"),
    LesserOrEqual("<="),
    Equals("="),
    NotEquals("!="),
    GreaterOrEqual(">="),
    Greater(">")
}