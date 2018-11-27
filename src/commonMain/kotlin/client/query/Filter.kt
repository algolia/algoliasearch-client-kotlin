package client.query


sealed class Filter(
    open val negates: kotlin.Boolean
) {

    abstract val raw: String

    data class Facet(
        val attribute: String,
        val value: String,
        override val negates: kotlin.Boolean = false
    ) : Filter(negates) {

        override val raw = "$attribute:$value"
    }

    data class Boolean(
        val attribute: String,
        val value: kotlin.Boolean,
        override val negates: kotlin.Boolean = false
    ) : Filter(negates) {

        override val raw = "$attribute:$value"
    }

    data class Tag(
        val value: String,
        override val negates: kotlin.Boolean = false
    ) : Filter(negates) {

        override val raw = "_tags:$value"
    }

    data class Comparison(
        val attribute: String,
        val operator: BooleanOperator,
        val value: Double,
        override val negates: kotlin.Boolean = false
    ) : Filter(negates) {

        override val raw = "$attribute ${operator.raw} $value"
    }

    data class Range(
        val attribute: String,
        val lowerBound: Double,
        val upperBound: Double,
        override val negates: kotlin.Boolean = false
    ) : Filter(negates) {

        override val raw = "$attribute $lowerBound TO $upperBound"
    }
}