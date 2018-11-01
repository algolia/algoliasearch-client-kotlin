package client.query


sealed class NumericFilter {

    abstract fun render(): String

    data class Comparison(val attribute: String, val operator: BooleanOperator, val value: Double) : NumericFilter() {

        override fun render() = "$attribute $operator $value"
    }

    data class Range(val attribute: String, val lowerBound: Double, val upperBound: Double) : NumericFilter() {

        override fun render() = "$attribute $lowerBound TO $upperBound"
    }
}