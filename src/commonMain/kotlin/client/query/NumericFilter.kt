package client.query


sealed class NumericFilter {

    abstract val raw: String

    data class Comparison(val attribute: String, val operator: BooleanOperator, val value: Double) : NumericFilter() {

        override val raw = "$attribute ${operator.raw} $value"
    }

    data class Range(val attribute: String, val lowerBound: Double, val upperBound: Double) : NumericFilter() {

        override val raw = "$attribute $lowerBound TO $upperBound"
    }
}