package client.query.helper


sealed class Filter(
    open val attribute: String,
    open val negates: kotlin.Boolean,
    open val group: Group?
) {

    protected abstract val expression: String

    fun build() = if (negates) "NOT $expression" else expression

    data class Facet(
        override val attribute: String,
        val value: String,
        override val negates: kotlin.Boolean = false,
        override val group: Group? = null
    ) : Filter(attribute, negates, group) {

        override val expression = "$attribute:$value"
    }

    data class Boolean(
        override val attribute: String,
        val value: kotlin.Boolean,
        override val negates: kotlin.Boolean = false,
        override val group: Group? = null
    ) : Filter(attribute, negates, group) {

        override val expression = "$attribute:$value"
    }

    data class Tag(
        val value: String,
        override val negates: kotlin.Boolean = false,
        override val group: Group? = null
    ) : Filter("_tags", negates, group) {

        override val expression = "$attribute:$value"
    }

    data class Comparison(
        override val attribute: String,
        val operator: NumericOperator,
        val value: Double,
        override val negates: kotlin.Boolean = false,
        override val group: Group? = null
    ) : Filter(attribute, negates, group) {

        override val expression = "$attribute ${operator.raw} $value"
    }

    data class Range(
        override val attribute: String,
        val lowerBound: Double,
        val upperBound: Double,
        override val negates: kotlin.Boolean = false,
        override val group: Group? = null
    ) : Filter(attribute, negates, group) {

        override val expression = "$attribute:$lowerBound TO $upperBound"
    }
}