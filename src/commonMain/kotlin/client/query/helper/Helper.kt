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

internal typealias GroupMap = MutableMap<Group.Key, MutableSet<Filter>>

internal fun GroupMap.add(group: Group, vararg filters: Filter) {
    filters.forEach { filter ->
        val key = group.key(filter)
        getOrElse(key) { mutableSetOf() }.let {
            it += filter
            this[key] = it
        }
    }
}

internal fun GroupMap.remove(group: Group, vararg filters: Filter): Boolean {
    return filters.any {
        get(group.key(it))?.remove(it) ?: false
    }
}

internal fun GroupMap.contains(group: Group, filter: Filter): Boolean {
    return get(group.key(filter))?.contains(filter) ?: false
}

internal fun GroupMap.clear(group: Group, attribute: Attribute?) {
    filterKeys { it.name == group.name }.forEach {
        if (attribute != null) {
            it.value.removeAll { it.attribute == attribute }
        } else {
            it.value.clear()
        }
        if (it.value.isEmpty()) remove(it.key)
    }
}

internal fun GroupMap.replaceAttribute(group: Group, attribute: Attribute, replacement: Attribute) {
    filterKeys { it.name == group.name }.forEach {
        val found = it.value.filter { it.attribute == attribute }

        it.value -= found
        it.value += found.map { it.modifyAttribute(replacement) }
    }
}

internal fun GroupMap.get(group: Group, attribute: Attribute?): Set<Filter> {
    val filters = filterKeys { it.name == group.name }.flatMap { it.value }

    return if (attribute != null) {
        filters.filter { it.attribute == attribute }
    } else {
        filters
    }.toSet()
}

private fun Filter.modifyAttribute(attribute: Attribute): Filter {
    return when (this) {
        is FilterComparison -> copy(attribute = attribute)
        is FilterTag -> this
        is FilterFacet -> copy(attribute = attribute)
        is FilterRange -> copy(attribute = attribute)
    }
}

fun Filter.not(): Filter {
    not = true
    return this
}