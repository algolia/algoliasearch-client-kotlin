package com.algolia.search.dsl.filtering

import com.algolia.search.model.Attribute


internal fun Attribute.escape() = "\"$raw\""

internal sealed class RangeValue<T : Number> {

    abstract val lowerBound: T
    abstract val upperBound: T

    protected class IllegalBoundsException(
        lowerBound: Number,
        upperBound: Number
    ) : IllegalArgumentException("upperBound: $upperBound should be lower than lowerBound: $lowerBound")


    data class Int(
        override val lowerBound: kotlin.Int,
        override val upperBound: kotlin.Int
    ) : RangeValue<kotlin.Int>() {

        init {
            if (upperBound < lowerBound) throw IllegalBoundsException(lowerBound, upperBound)
        }
    }

    data class Long(
        override val lowerBound: kotlin.Long,
        override val upperBound: kotlin.Long
    ) : RangeValue<kotlin.Long>() {

        init {
            if (upperBound < lowerBound) throw IllegalBoundsException(lowerBound, upperBound)
        }
    }

    data class Float(
        override val lowerBound: kotlin.Float,
        override val upperBound: kotlin.Float
    ) : RangeValue<kotlin.Float>() {

        init {
            if (upperBound < lowerBound) throw IllegalBoundsException(lowerBound, upperBound)
        }
    }

    data class Double(
        override val lowerBound: kotlin.Double,
        override val upperBound: kotlin.Double
    ) : RangeValue<kotlin.Double>() {

        init {
            if (upperBound < lowerBound) throw throw IllegalBoundsException(lowerBound, upperBound)
        }
    }
}

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