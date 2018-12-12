package client.query.helper

internal enum class FilterKey {
    And,
    OrFacet,
    OrNumeric,
    OrTag
}

sealed class Group(open val name: String) {

    internal data class Key(val name: String, val key: FilterKey)

    data class And(override val name: String) : Group(name)

    data class Or(override val name: String) : Group(name)
}

internal fun Group.key(filter: Filter): Group.Key {
    val key = when (this) {
        is Group.Or -> when (filter) {
            is FilterFacet -> FilterKey.OrFacet
            is FilterNumeric -> FilterKey.OrNumeric
            is FilterTag -> FilterKey.OrTag
        }
        is Group.And -> FilterKey.And
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