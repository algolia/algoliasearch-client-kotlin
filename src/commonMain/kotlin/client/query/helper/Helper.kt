package client.query.helper

internal typealias Filters<T> = MutableList<MutableList<T>>

internal fun <T : Filter> Filters<T>.and(vararg filter: T) {
    filter.forEach {
        this += mutableListOf(it)
    }
}

internal fun <T : Filter> Filters<T>.or(vararg filter: T) {
    this += filter.toMutableList()
}

internal fun <T : Filter> Filters<T>.replace(filter: T, replacement: T) {
    forEach {
        val index = it.indexOf(filter)

        if (index != -1) {
            it.removeAt(index)
            it.add(index, replacement)
        }
    }
}

internal fun <T : Filter> Filters<T>.remove(vararg filter: T) {
    filter.forEach {
        forEach { filters -> filters.remove(it) }
    }
    removeAll { it.isEmpty() }
}

internal fun <T : Filter> Filters<T>.getFilters(group: Group): List<T> {
    return flatMap {
        it.filter { it.group == group }
    }
}

internal fun <T : Filter> Filters<T>.clear(group: Group? = null) {
    if (group != null) {
        forEach {
            it.removeAll { it.group == group }
        }
        removeAll { it.isEmpty() }
    } else clear()
}

internal inline fun <reified T : Filter> Filters<T>.replaceAttribute(
    attribute: Attribute,
    replacement: Attribute,
    group: Group? = null
) {

    forEach { list ->
        val matches = list.filter {
            it.attribute == attribute && if (group != null) group == it.group else true
        }

        matches.forEach {
            val index = list.indexOf(it)

            list.removeAt(index)
            list.add(index, it.modifyAttribute(replacement) as T)
        }
    }
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