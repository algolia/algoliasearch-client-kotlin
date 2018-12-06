package client.query.helper

sealed class OptionalFilter(
    override val attribute: Attribute,
    override val negates: kotlin.Boolean,
    override val group: Group?
) : Filter(attribute, negates, group)


sealed class Filter(
    open val attribute: Attribute,
    open val negates: kotlin.Boolean,
    open val group: Group?
) {

    abstract val expression: String

    fun build() = if (negates) "NOT $expression" else expression
}

data class FilterFacet(
    override val attribute: Attribute,
    val value: String,
    override val negates: Boolean = false,
    override val group: Group? = null
) : OptionalFilter(attribute, negates, group) {

    override val expression = "$attribute:$value"
}

data class FilterBoolean(
    override val attribute: Attribute,
    val value: Boolean,
    override val negates: Boolean = false,
    override val group: Group? = null
) : OptionalFilter(attribute, negates, group) {

    override val expression = "$attribute:$value"
}

data class FilterTag(
    val value: String,
    override val negates: Boolean = false,
    override val group: Group? = null
) : Filter(Attribute("_tags"), negates, group) {

    override val expression = "$attribute:$value"
}

data class FilterComparison(
    override val attribute: Attribute,
    val operator: NumericOperator,
    val value: Double,
    override val negates: Boolean = false,
    override val group: Group? = null
) : Filter(attribute, negates, group) {

    override val expression = "$attribute ${operator.raw} $value"
}

data class FilterRange(
    override val attribute: Attribute,
    val lowerBound: Double,
    val upperBound: Double,
    override val negates: Boolean = false,
    override val group: Group? = null
) : Filter(attribute, negates, group) {

    override val expression = "$attribute:$lowerBound TO $upperBound"
}