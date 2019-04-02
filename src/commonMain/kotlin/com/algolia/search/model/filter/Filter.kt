package com.algolia.search.model.filter

import com.algolia.search.model.Attribute


public sealed class Filter {

    abstract val attribute: Attribute

    internal abstract val expression: String

    var isNegated = false

    fun build() = if (isNegated) "NOT $expression" else expression

    public data class Tag(
        val value: String
    ) : Filter() {

        override val attribute = Attribute("_tags")
        override val expression = "$attribute:\"$value\""

        override fun toString(): String {
            return "FilterTag($expression)"
        }
    }

    public data class Numeric internal constructor(
        override val attribute: Attribute,
        override val expression: String
    ) : Filter() {

        constructor(attribute: Attribute, range: IntRange) : this(
            attribute,
            expression(attribute, range.start, range.endInclusive)
        )

        constructor(attribute: Attribute, range: LongRange) : this(
            attribute,
            expression(attribute, range.start, range.endInclusive)
        )

        constructor(attribute: Attribute, lowerBound: Float, upperBound: Float) : this(
            attribute,
            expression(attribute, lowerBound, upperBound)
        )

        constructor(attribute: Attribute, lowerBound: Double, upperBound: Double) : this(
            attribute,
            expression(attribute, lowerBound, upperBound)
        )

        constructor(attribute: Attribute, operator: NumericOperator, value: Number) : this(
            attribute,
            "${attribute.escape()} ${operator.raw} $value"
        )

        override fun toString(): String {
            return "FilterNumeric($expression)"
        }

        companion object {

            private fun expression(attribute: Attribute, lowerBound: Number, upperBound: Number): String {
                return "${attribute.escape()}:$lowerBound TO $upperBound"
            }
        }
    }

    public data class Facet internal constructor(
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
}

public operator fun <T : Filter> T.not(): T {
    isNegated = !isNegated
    return this
}