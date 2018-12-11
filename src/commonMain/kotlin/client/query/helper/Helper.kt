package client.query.helper

import kotlin.reflect.KClass


internal data class GroupKey<T : Filter>(val name: String, val kClass: KClass<T>)

sealed class Group(open val name: String) {

    data class And(override val name: String) : Group(name)

    data class Or(override val name: String) : Group(name)
}

internal inline fun <reified T : Filter> Group.key(): GroupKey<T> {
    return GroupKey(name, T::class)
}

internal typealias GroupMap = MutableMap<GroupKey<*>, MutableSet<Filter>>

internal inline fun <reified T : Filter> GroupMap.add(group: Group, filters: Array<out T>) {
    val key = group.key<T>()
    getOrElse(key) { mutableSetOf() }.let {
        it += filters
        this[key] = it
    }
}

internal inline fun <reified T : Filter> GroupMap.remove(group: Group, filters: Array<out T>): Boolean {
    return get(group.key<T>())?.removeAll(filters) ?: false
}

internal inline fun <reified T : Filter> GroupMap.containsReified(group: Group, filter: T): Boolean {
    return get(group.key<T>())?.contains(filter) ?: false
}

internal fun GroupMap.contains(group: Group, filter: Filter): Boolean {
    return when (filter) {
        is FilterFacet -> containsReified(group, filter)
        is FilterNumeric -> containsReified(group, filter)
        is FilterTag -> containsReified(group, filter)
    }
}

internal fun GroupMap.clear(group: Group, attribute: Attribute?) {
    clearReified<FilterFacet>(group, attribute)
    clearReified<FilterNumeric>(group, attribute)
    clearReified<FilterTag>(group, attribute)
    clearReified<Filter>(group, attribute)
}

internal fun GroupMap.replaceAttribute(group: Group, attribute: Attribute, replacement: Attribute) {
    replaceAttributeReified<FilterFacet>(group, attribute, replacement)
    replaceAttributeReified<FilterNumeric>(group, attribute, replacement)
    replaceAttributeReified<FilterTag>(group, attribute, replacement)
    replaceAttributeReified<Filter>(group, attribute, replacement)
}

private inline fun <reified T : Filter> GroupMap.clearReified(group: Group, attribute: Attribute?) {
    val key = group.key<T>()

    get(key)?.let { set ->
        if (attribute != null) {
            set.removeAll { it.attribute == attribute }
        } else {
            set.clear()
        }
        if (set.isEmpty()) remove(key)
    }
}

private inline fun <reified T : Filter> GroupMap.replaceAttributeReified(
    group: Group,
    attribute: Attribute,
    replacement: Attribute
) {
    get(group.key<T>())?.let {
        val attributes = it.filter { it.attribute == attribute }

        it.removeAll(attributes)
        it += attributes.map { it.modifyAttribute(replacement) }
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