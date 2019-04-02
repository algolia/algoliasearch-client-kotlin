package com.algolia.search.model.filter


public enum class NumericOperator(val raw: String) {
    Lesser("<"),
    LesserOrEquals("<="),
    Equals("="),
    NotEquals("!="),
    GreaterOrEquals(">="),
    Greater(">")
}