package client.query


enum class NumericOperator(val raw: String) {
    Lesser("<"),
    LesserOrEqual("<="),
    Equals("="),
    NotEquals("!="),
    GreaterOrEqual(">="),
    Greater(">")
}