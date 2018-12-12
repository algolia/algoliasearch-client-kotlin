package client.query.helper

internal enum class ClassKey {
    Filter,
    FilterFacet,
    FilterNumeric,
    FilterTag
}

internal data class GroupKey(val name: String, val key: ClassKey)

sealed class Group(open val name: String) {

    data class And(override val name: String) : Group(name)

    data class Or(override val name: String) : Group(name)
}

internal fun Group.key(filter: Filter): GroupKey {
    val key = when (this) {
        is Group.And -> when (filter) {
            is FilterFacet -> ClassKey.FilterFacet
            is FilterNumeric -> ClassKey.FilterNumeric
            is FilterTag -> ClassKey.FilterTag
        }
        is Group.Or -> ClassKey.Filter
    }
    return GroupKey(name, key)
}

internal typealias GroupMap = MutableMap<GroupKey, MutableSet<Filter>>

internal fun GroupMap.add(group: Group, vararg filters: Filter) {
    filters.forEach {
        val key = group.key(it)
        getOrElse(key) { mutableSetOf() }.let {
            it += filters
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