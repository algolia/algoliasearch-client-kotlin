package com.algolia.search.filter

import com.algolia.search.model.Attribute


public sealed class Filter {

    abstract val attribute: Attribute

    internal abstract val expression: String

    var isNegated = false
        private set

    operator fun not(): Filter {
        isNegated = !isNegated
        return this
    }

    operator fun unaryPlus(): Filter {
        isNegated = false
        return this
    }

    operator fun unaryMinus(): Filter {
        isNegated = true
        return this
    }

    fun build() = if (isNegated) "NOT $expression" else expression
}

public data class FilterTag(
    val value: String
) : Filter() {

    override val attribute = Attribute("_tags")
    override val expression = "$attribute:\"$value\""

    override fun toString(): String {
        return "FilterTag($expression)"
    }
}

public sealed class FilterNumeric : Filter() {

    override fun toString(): String {
        return "FilterNumeric($expression)"
    }
}

public data class FilterComparison(
    override val attribute: Attribute,
    val operator: NumericOperator,
    val value: Number
) : FilterNumeric() {

    override val expression = "${attribute.escape()} ${operator.raw} $value"

    override fun toString(): String {
        return "FilterComparison($expression)"
    }
}

public data class FilterRange internal constructor(
    override val attribute: Attribute,
    private val rangeValue: RangeValue<*>
) : FilterNumeric() {

    override val expression = "${attribute.escape()}:${rangeValue.lowerBound} TO ${rangeValue.upperBound}"

    constructor(attribute: Attribute, lowerBound: Int, upperBound: Int) : this(
        attribute,
        RangeValue.Int(lowerBound, upperBound)
    )

    constructor(attribute: Attribute, lowerBound: Long, upperBound: Long) : this(
        attribute,
        RangeValue.Long(lowerBound, upperBound)
    )

    constructor(attribute: Attribute, lowerBound: Float, upperBound: Float) : this(
        attribute,
        RangeValue.Float(lowerBound, upperBound)
    )

    constructor(attribute: Attribute, lowerBound: Double, upperBound: Double) : this(
        attribute,
        RangeValue.Double(lowerBound, upperBound)
    )

    constructor(attribute: Attribute, range: IntRange) : this(
        attribute,
        RangeValue.Int(range.start, range.endInclusive)
    )

    constructor(attribute: Attribute, range: LongRange) : this(
        attribute,
        RangeValue.Long(range.start, range.endInclusive)
    )

    override fun toString(): String {
        return "FilterRange($expression)"
    }
}

public data class FilterFacet internal constructor(
    override val attribute: Attribute,
    private val value: FacetValue<*>,
    private val score: Int? = null
) : Filter() {

    public constructor(attribute: Attribute, value: String, score: Int? = null) : this(
        attribute,
        FacetValue.String(value),
        score
    )

    public constructor(attribute: Attribute, value: Boolean, score: Int? = null) : this(
        attribute,
        FacetValue.Boolean(value),
        score
    )

    public constructor(attribute: Attribute, value: Number, score: Int? = null) : this(
        attribute,
        FacetValue.Number(value),
        score
    )

    override val expression: String =
        "${attribute.escape()}:${value.escape()}" + if (score != null) "<score=$score>" else ""

    override fun toString(): String {
        return "FilterFacet($expression)"
    }
}

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

internal fun Attribute.escape() = "\"$raw\""