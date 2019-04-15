package com.algolia.search.model.filter


public sealed class FilterGroupsConverter<I, O> : (I) -> O {

    public object SQL : FilterGroupsConverter<List<FilterGroup<*>>, String>() {

        override fun invoke(groups: List<FilterGroup<*>>): String {
            val (andEntries, orEntries) = groups.partition { it is FilterGroup.And }
            val ands = andEntries.joinToString(separator = " AND ") { group ->
                group.joinToString(prefix = "(", postfix = ")", separator = " AND ") { FilterConverter.SQL(it) }
            }
            val begin = if (andEntries.isNotEmpty() && orEntries.isNotEmpty()) " AND " else ""
            val ors = orEntries.joinToString(prefix = begin, separator = " AND ") { group ->
                group.joinToString(prefix = "(", postfix = ")", separator = " OR ") { FilterConverter.SQL(it) }
            }
            return ands + ors
        }
    }

    public sealed class Legacy<T: Filter> : FilterGroupsConverter<List<FilterGroup<T>>, List<List<String>>>() {

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

    public object SQLUnquoted : FilterGroupsConverter<List<FilterGroup<*>>, String>() {

        override fun invoke(groups: List<FilterGroup<*>>): String {
            return SQL(groups).replace("\"", "")
        }
    }
}