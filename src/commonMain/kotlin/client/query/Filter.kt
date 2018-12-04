package client.query


sealed class Filter(
    open val attribute: String,
    open val negates: kotlin.Boolean,
    open val variant: String?
) {

    protected abstract val expression: String

    fun raw() = if (negates) "NOT $expression" else expression

    data class Facet(
        override val attribute: String,
        val value: String,
        override val negates: kotlin.Boolean = false,
        override val variant: String? = null
    ) : Filter(attribute, negates, variant) {

        override val expression = "$attribute:$value"
    }

    data class Boolean(
        override val attribute: String,
        val value: kotlin.Boolean,
        override val negates: kotlin.Boolean = false,
        override val variant: String? = null
    ) : Filter(attribute, negates, variant) {

        override val expression = "$attribute:$value"
    }

    data class Tag(
        override val attribute: String,
        override val negates: kotlin.Boolean = false,
        override val variant: String? = null
    ) : Filter(attribute, negates, variant) {

        override val expression = "_tags:$attribute"
    }

    data class Comparison(
        override val attribute: String,
        val operator: BooleanOperator,
        val value: Double,
        override val negates: kotlin.Boolean = false,
        override val variant: String? = null
    ) : Filter(attribute, negates, variant) {

        override val expression = "$attribute ${operator.raw} $value"
    }

    data class Range(
        override val attribute: String,
        val lowerBound: Double,
        val upperBound: Double,
        override val negates: kotlin.Boolean = false,
        override val variant: String? = null
    ) : Filter(attribute, negates, variant) {

        override val expression = "$attribute:$lowerBound TO $upperBound"
    }
}