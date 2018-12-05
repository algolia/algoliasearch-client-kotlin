package client.query.helper

import client.query.Filter
import client.query.Query


class FilterHelper {

    private val filters = mutableListOf<MutableList<Filter>>()

    fun addFilterAnd(vararg filter: Filter): FilterHelper {
        filter.forEach {
            filters += mutableListOf(it)
        }
        return this
    }

    fun addFilterOr(first: Filter.Facet, second: Filter.Facet, vararg filter: Filter.Facet): FilterHelper {
        return addDisjunctiveGroupInternal(first, second, *filter)
    }

    fun addFilterOr(first: Filter.Boolean, second: Filter.Boolean, vararg filter: Filter.Boolean): FilterHelper {
        return addDisjunctiveGroupInternal(first, second, *filter)
    }

    fun addFilterOr(first: Filter.Tag, second: Filter.Range, vararg filter: Filter.Tag): FilterHelper {
        return addDisjunctiveGroupInternal(first, second, *filter)
    }

    fun addFilterOr(
        first: Filter.Comparison,
        second: Filter.Comparison,
        vararg filter: Filter.Comparison
    ): FilterHelper {
        return addDisjunctiveGroupInternal(first, second, *filter)
    }

    fun addFilterOr(first: Filter.Range, second: Filter.Range, vararg filter: Filter.Range): FilterHelper {
        return addDisjunctiveGroupInternal(first, second, *filter)
    }

    private fun addDisjunctiveGroupInternal(vararg filter: Filter): FilterHelper {
        filters += mutableListOf(*filter)
        return this
    }

    fun replace(filter: Filter.Facet, replacement: Filter.Facet) {
        replaceInternal(filter, replacement)
    }

    fun replace(filter: Filter.Boolean, replacement: Filter.Boolean) {
        replaceInternal(filter, replacement)
    }

    fun replace(filter: Filter.Tag, replacement: Filter.Tag) {
        replaceInternal(filter, replacement)
    }

    fun replace(filter: Filter.Comparison, replacement: Filter.Comparison) {
        replaceInternal(filter, replacement)
    }

    fun replace(filter: Filter.Range, replacement: Filter.Range) {
        replaceInternal(filter, replacement)
    }

    private fun replaceInternal(filter: Filter, replacement: Filter) {
        filters.forEach { filters ->
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

    fun clear() {
        filters.clear()
    }

    fun get(variant: String): List<Filter> {
        return filters.flatMap {
            it.filter { it.variant == variant }
        }
    }

    fun build(vararg query: Query) {
        query.forEach {
            it.filters = build()
        }
    }

    fun build(): String {
        return filters.joinToString(separator = " AND ") { group ->
            val prefix = if (group.size == 1 || filters.size == 1) "" else "("
            val postfix = if (group.size == 1 || filters.size == 1) "" else ")"

            group.joinToString(prefix = prefix, postfix = postfix, separator = " OR ") {
                it.raw()
            }
        }
    }
}