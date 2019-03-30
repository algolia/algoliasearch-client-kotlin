package com.algolia.search.dsl.filter

import com.algolia.search.model.Attribute


public sealed class Filter {

    abstract val attribute: Attribute

    internal abstract val expression: String

    var isNegated = false

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

    constructor(attribute: Attribute, range: IntRange) : this(
        attribute,
        RangeValue.Int(range.start, range.endInclusive)
    )

    constructor(attribute: Attribute, range: LongRange) : this(
        attribute,
        RangeValue.Long(range.start, range.endInclusive)
    )

    constructor(attribute: Attribute, lowerBound: Float, upperBound: Float) : this(
        attribute,
        RangeValue.Float(lowerBound, upperBound)
    )

    constructor(attribute: Attribute, lowerBound: Double, upperBound: Double) : this(
        attribute,
        RangeValue.Double(lowerBound, upperBound)
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

public operator fun <T : Filter> T.not(): T {
    isNegated = !isNegated
    return this
}