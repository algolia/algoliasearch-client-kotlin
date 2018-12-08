package client.query.helper


sealed class Filter(
    open val attribute: Attribute,
    open val negates: Boolean
) {

    abstract val expression: String

    fun build() = if (negates) "NOT $expression" else expression
}

sealed class FacetFilter(
    override val attribute: Attribute,
    override val negates: Boolean
) : Filter(attribute, negates)

sealed class NumericFilter(
    override val attribute: Attribute,
    override val negates: Boolean
) : Filter(attribute, negates)

data class FilterFacet(
    override val attribute: Attribute,
    val value: String,
    override val negates: Boolean = false
) : FacetFilter(attribute, negates) {

    override val expression = "$attribute:$value"
}

data class FilterBoolean(
    override val attribute: Attribute,
    val value: Boolean,
    override val negates: Boolean = false
) : FacetFilter(attribute, negates) {

    override val expression = "$attribute:$value"
}

data class FilterTag(
    val value: String,
    override val negates: Boolean = false
) : Filter(Attribute("_tags"), negates) {

    override val expression = "$attribute:$value"
}

data class FilterComparison(
    override val attribute: Attribute,
    val operator: NumericOperator,
    val value: Double,
    override val negates: Boolean = false
) : NumericFilter(attribute, negates) {

    override val expression = "$attribute ${operator.raw} $value"
}

data class FilterRange(
    override val attribute: Attribute,
    val lowerBound: Double,
    val upperBound: Double,
    override val negates: Boolean = false
) : NumericFilter(attribute, negates) {

    override val expression = "$attribute:$lowerBound TO $upperBound"
}