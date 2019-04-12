package com.algolia.search.model.filter


public sealed class FilterGroupConverter<I, O> : (I) -> O {

    public object SQL : FilterGroupConverter<List<FilterGroup<*>>, String>() {

        override fun invoke(groups: List<FilterGroup<*>>): String {
            val (andEntries, orEntries) = groups.partition { it is FilterGroup.And }
            val ands = andEntries.joinToString(separator = " AND ") { group ->
                val condition = andEntries.size > 1 && group.size > 1
                val prefix = if (condition) "(" else ""
                val postfix = if (condition) ")" else ""

                group.joinToString(prefix = prefix, postfix = postfix, separator = " AND ") { FilterConverter.SQL(it) }
            }
            val begin = if (andEntries.isNotEmpty() && orEntries.isNotEmpty()) " AND " else ""
            val ors = orEntries.joinToString(prefix = begin, separator = " AND ") { group ->
                val condition = group.size > 1 && (orEntries.size > 1 || andEntries.isNotEmpty())
                val prefix = if (condition) "(" else ""
                val postfix = if (condition) ")" else ""

                group.joinToString(prefix = prefix, postfix = postfix, separator = " OR ") { FilterConverter.SQL(it) }
            }
            return ands + ors
        }
    }

    public sealed class Legacy<T: Filter> : FilterGroupConverter<List<FilterGroup<T>>, List<List<String>>>() {

        override fun invoke(groups: List<FilterGroup<T>>): List<List<String>> {
            val (andEntries, orEntries) = groups.partition { it is FilterGroup.And }
            val ands = andEntries.flatMap { group -> group.flatMap { FilterConverter.Legacy(it) } }.map { listOf(it) }
            val ors = orEntries.map { group -> group.flatMap { FilterConverter.Legacy(it) } }

            return ands + ors
        }

        object Facet: Legacy<Filter.Facet>()

        object Tag: Legacy<Filter.Tag>()

        object Numeric: Legacy<Filter.Numeric>()
    }

    internal object SQLUnquoted : FilterGroupConverter<List<FilterGroup<*>>, String>() {

        override fun invoke(groups: List<FilterGroup<*>>): String {
            return SQL(groups).replace("\"", "")
        }
    }
}