package client.query.helper


sealed class Filter(
    open val attribute: Attribute
) {

    internal var not = false

    abstract val expression: String

    fun build() = if (not) "NOT $expression" else expression
}

sealed class NumericFilter(
    override val attribute: Attribute
) : Filter(attribute)

data class FilterTag(
    val value: String
) : Filter(Attribute("_tags")) {

    override val expression = "$attribute:$value"
}

data class FilterFacet internal constructor(
    override val attribute: Attribute,
    private val string: String? = null,
    private val boolean: Boolean? = null
) : Filter(attribute) {

    constructor(attribute: Attribute, string: String) : this(attribute, string, null)

    constructor(attribute: Attribute, boolean: Boolean) : this(attribute, null, boolean)

    override val expression: String = if (string != null) "$attribute:$string" else "$attribute:$boolean"
}

data class FilterComparison(
    override val attribute: Attribute,
    val operator: NumericOperator,
    val value: Double
) : NumericFilter(attribute) {

    override val expression = "$attribute ${operator.raw} $value"
}

data class FilterRange(
    override val attribute: Attribute,
    val lowerBound: Double,
    val upperBound: Double
) : NumericFilter(attribute) {

    override val expression = "$attribute:$lowerBound TO $upperBound"
}