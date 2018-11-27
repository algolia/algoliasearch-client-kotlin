package client.query.helper

import client.query.Filter


class FilterHelper {

    private val filters = mutableListOf<MutableList<Filter>>()

    fun add(vararg filter: Filter) {
        filter.forEach {
            filters += mutableListOf(it)
        }
    }

    fun addDisjunctiveGroup(vararg filter: Filter) {
        filters += filter.toMutableList()
    }

    fun remove(vararg filter: Filter) {
        filter.forEach {
            filters.forEach { filters -> filters.remove(it) }
            val clean = filters.filterNot { it.isEmpty() }
            filters.clear()
            filters.addAll(clean)
        }
    }

    fun raw(): String {
        return filters.joinToString(separator = " AND ") { group ->
            val prefix = if (group.size == 1) "" else "("
            val postfix = if (group.size == 1) "" else ")"
            val expression = group.joinToString(prefix = prefix, postfix = postfix, separator = " OR ") {
                if (it.negates && group.indexOf(it) > 0) "NOT ${it.raw}" else it.raw
            }

            if (filters.indexOf(group) > 0 && group.size == 1 && group.first().negates) "NOT $expression" else expression
        }
    }
}