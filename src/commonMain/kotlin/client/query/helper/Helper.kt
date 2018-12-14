package client.query.helper

internal enum class FilterKey {
    And,
    OrFacet,
    OrNumeric,
    OrTag
}

internal fun Group.key(filter: Filter): Group.Key {
    val key = when (this) {
        is GroupOr -> when (filter) {
            is FilterFacet -> FilterKey.OrFacet
            is FilterNumeric -> FilterKey.OrNumeric
            is FilterTag -> FilterKey.OrTag
        }
        is GroupAnd -> FilterKey.And
    }
    return Group.Key(name, key)
}

internal typealias GroupMap<T> = MutableMap<Group.Key, MutableSet<T>>

internal fun <T : Filter> GroupMap<T>.add(group: Group, vararg filters: T) {
    filters.forEach { filter ->
        val key = group.key(filter)

        getOrElse(key) { mutableSetOf() }.let {
            it += filter
            this[key] = it
        }
    }
}

internal fun <T : Filter> GroupMap<T>.remove(group: Group, vararg filters: Filter): Boolean {
    return filters.any { get(group.key(it))?.remove(it) ?: false }
}

internal fun <T : Filter> GroupMap<T>.contains(group: Group, filter: Filter): Boolean {
    return get(group.key(filter))?.contains(filter) ?: false
}

internal fun <T : Filter> GroupMap<T>.clear(group: Group, attribute: Attribute?) {
    filterKeys { it.name == group.name }.forEach {
        if (attribute != null) {
            it.value.removeAll { it.attribute == attribute }
        } else {
            it.value.clear()
        }
        if (it.value.isEmpty()) remove(it.key)
    }
}

internal fun <T : Filter> GroupMap<T>.replace(group: Group, filter: T, replacement: T): Boolean {
    if (filter::class != replacement::class) throw Exception("Can't replace filters of different types. [$filter, $replacement]")
    return get(group.key(filter))?.let {
        if (it.remove(filter)) {
            it += replacement
            true
        } else false
    } ?: false
}

internal fun <T : Filter> GroupMap<T>.move(origin: Group, destination: Group, filter: T): Boolean {
    return get(origin.key(filter))?.let {
        if (it.remove(filter)) {
            add(destination, filter)
            true
        } else false
    } ?: false
}

internal inline fun <reified T : Filter> GroupMap<T>.replaceAttribute(
    group: Group,
    attribute: Attribute,
    replacement: Attribute
) {
    filterKeys { it.name == group.name }.forEach {
        val found = it.value.filter { it.attribute == attribute }

        it.value -= found
        it.value += found.map { it.modifyAttribute<T>(replacement) }
    }
}

internal fun <T : Filter> GroupMap<T>.get(group: Group, attribute: Attribute?): Set<T> {
    val filters = filterKeys { it.name == group.name }.flatMap { it.value }

    return if (attribute != null) {
        filters.filter { it.attribute == attribute }
    } else {
        filters
    }.toSet()
}

private inline fun <reified T> Filter.modifyAttribute(attribute: Attribute): T {
    return when (this) {
        is FilterComparison -> copy(attribute = attribute)
        is FilterTag -> this
        is FilterFacet -> copy(attribute = attribute)
        is FilterRange -> copy(attribute = attribute)
    } as T
}