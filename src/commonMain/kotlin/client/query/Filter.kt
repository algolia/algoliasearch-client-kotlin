package client.query


sealed class Filter {

    abstract val raw: String

    data class Facet(val attribute: String, val value: String, val negates: kotlin.Boolean = false): Filter() {

        override val raw = if (negates) "$attribute:-$value" else "$attribute:$value"
    }

    data class Boolean(val attribute: String, val value: kotlin.Boolean): Filter() {

        override val raw = "$attribute:$value"
    }

    data class Tag(val value: String): Filter() {

        override val raw = "_tags:$value"
    }

    sealed class Numeric: Filter() {

        data class Comparison(val attribute: String, val operator: BooleanOperator, val value: Double) : Numeric() {

            override val raw = "$attribute ${operator.raw} $value"
        }

        data class Range(val attribute: String, val lowerBound: Double, val upperBound: Double) : Numeric() {

            override val raw = "$attribute $lowerBound TO $upperBound"
        }
    }
}