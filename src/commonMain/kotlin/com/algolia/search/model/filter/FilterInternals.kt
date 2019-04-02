package com.algolia.search.model.filter

import com.algolia.search.model.Attribute


internal fun Attribute.escape() = "\"$raw\""

internal sealed class FacetValue<T> {

    abstract val value: T

    fun escape(): kotlin.String {
        return when (this) {
            is String -> "\"$value\""
            is Boolean -> value.toString()
            is Number -> value.toString()
        }
    }

    data class String(override val value: kotlin.String) : FacetValue<kotlin.String>()

    data class Boolean(override val value: kotlin.Boolean) : FacetValue<kotlin.Boolean>()

    data class Number(override val value: kotlin.Number) : FacetValue<kotlin.Number>()
}