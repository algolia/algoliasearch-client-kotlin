package client.query.helper

class IncompatibleFilterTypeException(
    filterA: Filter,
    filterB: Filter
) : Exception(
    "Incompatible filters of type ${filterA::class.simpleName} and " +
        "${filterB::class.simpleName} with the same attribute \"${filterB.attribute}\""
)

internal data class Filters<T : Filter>(
    val ands: MutableSet<T> = mutableSetOf(),
    val ors: MutableSet<T> = mutableSetOf()
)

internal fun <T : Filter> Filters<T>.and(vararg filters: T) {
    ands += filters
}

internal fun <T : Filter> Filters<T>.or(vararg filters: T) {
    filters.forEach { filter ->
        ors.find { or ->
            filter.attribute == or.attribute && when (filter) {
                is NumericFilter -> or !is NumericFilter
                is FacetFilter -> or !is FacetFilter
                is FilterTag -> or !is FilterTag
                else -> throw Exception("This should not happen.")
            }
        }.let {
            if (it != null) throw IncompatibleFilterTypeException(it, filter)
        }
        ors += filter
    }
}

internal fun <T : Filter> Filters<T>.replace(filter: T, replacement: T) {
    if (ands.remove(filter)) ands += replacement
    if (ors.remove(filter)) ors += replacement
}

internal fun <T : Filter> Filters<T>.remove(vararg filters: T) {
    ands.removeAll(filters)
    ors.removeAll(filters)
}

internal fun <T : Filter> Filters<T>.getFilters(attribute: Attribute? = null): Set<T> {
    return if (attribute != null) {
        ands.filter { it.attribute == attribute }.toSet() + ors.filter { it.attribute == attribute }
    } else {
        ands + ors
    }
}

internal fun <T : Filter> Filters<T>.clear(attribute: Attribute? = null) {
    if (attribute != null) {
        ands.removeAll { it.attribute == attribute }
        ors.removeAll { it.attribute == attribute }
    } else {
        ands.clear()
        ors.clear()
    }
}

internal inline fun <reified T : Filter> Filters<T>.replaceAttribute(
    attribute: Attribute,
    replacement: Attribute
) {
    val ands = ands.filter { it.attribute == attribute }
    val ors = ors.filter { it.attribute == attribute }

    this.ands.removeAll(ands)
    this.ors.removeAll(ors)
    this.ands += ands.map { it.modifyAttribute(replacement) as T }
    this.ors += ors.map { it.modifyAttribute(replacement) as T }
}

internal fun Filter.modifyAttribute(attribute: Attribute): Filter {
    return when (this) {
        is FilterComparison -> copy(attribute = attribute)
        is FilterTag -> this
        is FilterBoolean -> copy(attribute = attribute)
        is FilterFacet -> copy(attribute = attribute)
        is FilterRange -> copy(attribute = attribute)
    }
}