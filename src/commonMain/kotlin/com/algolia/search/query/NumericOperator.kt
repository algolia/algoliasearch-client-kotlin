package com.algolia.search.query


public enum class NumericOperator(val raw: String) {
    Lesser("<"),
    LesserOrEqual("<="),
    Equals("="),
    NotEquals("!="),
    GreaterOrEqual(">="),
    Greater(">")
}