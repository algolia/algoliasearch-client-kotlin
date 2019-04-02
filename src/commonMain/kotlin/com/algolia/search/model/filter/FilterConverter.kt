package com.algolia.search.model.filter


public sealed class FilterConverter<I, O> : (I) -> O {

    public object SQL : FilterConverter<List<FilterGroup<*>>, String>() {

        override fun invoke(groups: List<FilterGroup<*>>): String {
            val (andEntries, orEntries) = groups.partition { it is FilterGroup.And }
            val ands = andEntries.joinToString(separator = " AND ") { group ->
                val condition = andEntries.size > 1 && group.size > 1
                val prefix = if (condition) "(" else ""
                val postfix = if (condition) ")" else ""
                group.joinToString(prefix = prefix, postfix = postfix, separator = " AND ") { it.build() }
            }
            val begin = if (andEntries.isNotEmpty() && orEntries.isNotEmpty()) " AND " else ""
            val ors = orEntries.joinToString(prefix = begin, separator = " AND ") { group ->
                val condition = group.size > 1 && (orEntries.size > 1 || andEntries.isNotEmpty())
                val prefix = if (condition) "(" else ""
                val postfix = if (condition) ")" else ""
                group.joinToString(prefix = prefix, postfix = postfix, separator = " OR ") { it.build() }
            }
            return ands + ors
        }
    }

    public object Legacy : FilterConverter<List<FilterGroup<*>>, List<List<String>>>() {

        override fun invoke(groups: List<FilterGroup<*>>): List<List<String>> {
            val (andEntries, orEntries) = groups.partition { it is FilterGroup.And }
            val ands = andEntries.flatMap { group -> group.map { listOf(it.expression) } }
            val ors = orEntries.map { group -> group.map { it.expression } }

            return ands + ors
        }
    }

    internal object SQLUnquoted : FilterConverter<List<FilterGroup<*>>, String>() {

        override fun invoke(groups: List<FilterGroup<*>>): String {
            return SQL(groups).replace("\"", "")
        }
    }
}