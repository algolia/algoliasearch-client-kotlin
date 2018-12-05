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

    fun replace(filter: Filter.Facet, replacement: Filter.Facet): FilterHelper {
        return replaceInternal(filter, replacement)
    }

    fun replace(filter: Filter.Boolean, replacement: Filter.Boolean): FilterHelper {
        return replaceInternal(filter, replacement)
    }

    fun replace(filter: Filter.Tag, replacement: Filter.Tag): FilterHelper {
        return replaceInternal(filter, replacement)
    }

    fun replace(filter: Filter.Comparison, replacement: Filter.Comparison): FilterHelper {
        return replaceInternal(filter, replacement)
    }

    fun replace(filter: Filter.Range, replacement: Filter.Range): FilterHelper {
        return replaceInternal(filter, replacement)
    }

    private fun replaceInternal(filter: Filter, replacement: Filter): FilterHelper {
        filters.forEach { filters ->
            val index = filters.indexOf(filter)

            if (index != -1) {
                filters.removeAt(index)
                filters.add(index, replacement)
            }
        }
        return this
    }

    fun remove(vararg filter: Filter): FilterHelper {
        filter.forEach {
            filters.forEach { filters -> filters.remove(it) }
        }
        filters.removeAll { it.isEmpty() }
        return this
    }

    fun clear(variant: String): FilterHelper {
        filters.forEach {
            it.removeAll { it.variant == variant }
        }
        filters.removeAll { it.isEmpty() }
        return this
    }

    /**
     *  If a variant is specified, only filter using this variant will have its attribute replaced
     */
    fun replaceAttribute(attribute: String, replacement: String, variant: String? = null): FilterHelper {
        filters.forEach { filters ->
            val list =
                filters.filter {
                    it.attribute == attribute && if (variant != null) variant == it.variant else true
                }

            list.forEach {
                val index = filters.indexOf(it)

                filters.removeAt(index)
                filters.add(index, modifyAttribute(it, replacement))
            }
        }
        return this
    }

    private fun modifyAttribute(filter: Filter, attribute: String): Filter {
        return when (filter) {
            is Filter.Comparison -> filter.copy(attribute = attribute)
            is Filter.Tag -> filter
            is Filter.Boolean -> filter.copy(attribute = attribute)
            is Filter.Facet -> filter.copy(attribute = attribute)
            is Filter.Range -> filter.copy(attribute = attribute)
        }
    }

    fun clear(): FilterHelper {
        filters.clear()
        return this
    }

    fun getVariant(variant: String): List<Filter> {
        return filters.flatMap {
            it.filter { it.variant == variant }
        }
    }

    fun assign(vararg query: Query) {
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