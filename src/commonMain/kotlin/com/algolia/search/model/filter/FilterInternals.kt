package com.algolia.search.model.filter

import com.algolia.search.model.Attribute


internal fun String.escape() = "\"$this\""

internal fun Attribute.escape() = raw.escape()

internal fun Filter.Facet.Value<*>.toSQL(): String {
    return when (this) {
        is Filter.Facet.Value.String -> raw.escape()
        is Filter.Facet.Value.Number -> raw.toString()
        is Filter.Facet.Value.Boolean -> raw.toString()
    }
}

internal fun Filter.Facet.toSQL(): String {
    val value = value.toSQL()
    val attribute = attribute.escape()
    val score = if (score != null) "<score=$score>" else ""
    val expression = "$attribute:$value$score"

    return if (isNegated) "NOT $expression" else expression
}

internal fun Filter.Tag.toSQL(): String {
    val expression = "$attribute:$value"

    return if (isNegated) "NOT $expression" else expression
}

internal fun Filter.Numeric.Value.Comparison.toSQL(attribute: Attribute, isNegated: Boolean): String {
    val expression = "${attribute.escape()} ${operator.raw} $number"

    return if (isNegated) "NOT $expression" else expression
}

internal fun Filter.Numeric.Value.Range.toSQL(attribute: Attribute, isNegated: Boolean): String {
    val expression = "${attribute.escape()}:$lowerBound TO $upperBound"

    return if (isNegated) "NOT $expression" else expression
}

internal fun Filter.Numeric.toSQL(): String {
    return when (value) {
        is Filter.Numeric.Value.Comparison -> value.toSQL(attribute, isNegated)
        is Filter.Numeric.Value.Range -> value.toSQL(attribute, isNegated)
    }
}

internal fun Filter.Numeric.Value.Comparison.toLegacy(attribute: Attribute, isNegated: Boolean): List<String> {
    val operator = if (isNegated) {
        when (operator) {
            NumericOperator.Less -> NumericOperator.GreaterOrEquals
            NumericOperator.LessOrEquals -> NumericOperator.Greater
            NumericOperator.Equals -> NumericOperator.NotEquals
            NumericOperator.NotEquals -> NumericOperator.Equals
            NumericOperator.Greater -> NumericOperator.LessOrEquals
            NumericOperator.GreaterOrEquals -> NumericOperator.Less
        }
    } else operator

    return listOf("${attribute.escape()} ${operator.raw} $number")
}

internal fun Filter.Numeric.Value.Range.toLegacy(attribute: Attribute, isNegated: Boolean): List<String> {
    val attributeEscaped = attribute.escape()

    return if (isNegated) {
        listOf("$attributeEscaped < $lowerBound", "$attributeEscaped > $upperBound")
    } else {
        listOf("$attributeEscaped >= $lowerBound", "$attributeEscaped <= $upperBound")
    }
}

internal fun Filter.Numeric.toLegacy(): List<String> {
    return when (value) {
        is Filter.Numeric.Value.Range -> value.toLegacy(attribute, isNegated)
        is Filter.Numeric.Value.Comparison -> value.toLegacy(attribute, isNegated)
    }
}

internal fun Filter.Facet.Value<*>.toLegacy(isNegated: Boolean): String {
    val value = when (this) {
        is Filter.Facet.Value.String -> raw.escape()
        is Filter.Facet.Value.Number -> raw.toString()
        is Filter.Facet.Value.Boolean -> raw.toString()
    }
    return if (isNegated) "-$value" else value
}

internal fun Filter.Facet.toLegacy(): List<String> {
    val value = value.toLegacy(isNegated)
    val attribute = attribute.escape()
    val score = if (score != null) "<score=$score>" else ""

    return listOf("$attribute:$value$score")
}

internal fun Filter.Tag.toLegacy(): List<String> {
    val value = if (isNegated) "-$value" else value

    return listOf("$attribute:$value")
}
