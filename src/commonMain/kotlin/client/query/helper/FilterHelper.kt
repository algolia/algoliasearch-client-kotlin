package client.query.helper

import client.query.Filter
import client.query.Query


class FilterHelper {

    private val filters = mutableListOf<MutableList<Filter>>()

    fun add(vararg filter: Filter): FilterHelper {
        filter.forEach {
            filters += mutableListOf(it)
        }
        return this
    }

    private fun addDisjunctiveGroupInternal(vararg filter: Filter): FilterHelper {
        filters += mutableListOf(*filter)
        return this
    }

    fun addDisjunctiveGroup(vararg filter: Filter.Boolean): FilterHelper {
        return addDisjunctiveGroupInternal(*filter)
    }

    fun addDisjunctiveGroup(vararg filter: Filter.Comparison): FilterHelper {
        return addDisjunctiveGroupInternal(*filter)
    }

    fun addDisjunctiveGroup(vararg filter: Filter.Range): FilterHelper {
        return addDisjunctiveGroupInternal(*filter)
    }

    fun addDisjunctiveGroup(vararg filter: Filter.Tag): FilterHelper {
        return addDisjunctiveGroupInternal(*filter)
    }

    fun addDisjunctiveGroup(vararg filter: Filter.Facet): FilterHelper {
        return addDisjunctiveGroupInternal(*filter)
    }

    fun replace(filter: Filter, replacement: Filter) {
        filters.forEach {  filters ->
            val index = filters.indexOf(filter)

            if (index != -1) {
                filters.removeAt(index)
                filters.add(index, replacement)
            }
        }
    }

    fun remove(vararg filter: Filter): FilterHelper {
        filter.forEach {
            filters.forEach { filters -> filters.remove(it) }
            val clean = filters.filterNot { it.isEmpty() }
            filters.clear()
            filters.addAll(clean)
        }
        return this
    }

    fun assign(vararg query: Query) {
        query.forEach {
            it.filters = raw()
        }
    }

    fun raw(): String {
        return filters.joinToString(separator = " AND ") { group ->
            val prefix = if (group.size == 1 || filters.size == 1) "" else "("
            val postfix = if (group.size == 1 || filters.size == 1) "" else ")"

            group.joinToString(prefix = prefix, postfix = postfix, separator = " OR ") {
                it.raw()
            }
        }
    }
}