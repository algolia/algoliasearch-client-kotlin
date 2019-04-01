package com.algolia.search.dsl.filtering


public sealed class FilterBuilder<T> : (List<Group<*>>) -> T {

    public object SQL : FilterBuilder<String>() {

        override fun invoke(groups: List<Group<*>>): String {
            val (andEntries, orEntries) = groups.partition { it is GroupAnd }
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

    public object Legacy : FilterBuilder<List<List<String>>>() {

        override fun invoke(groups: List<Group<*>>): List<List<String>> {
            val (andEntries, orEntries) = groups.partition { it is GroupAnd }
            val ands = andEntries.flatMap { group -> group.map { listOf(it.expression) } }
            val ors = orEntries.map { group -> group.map { it.expression } }

            return ands + ors
        }
    }

    internal object SQLUnquoted : FilterBuilder<String>() {

        override fun invoke(groups: List<Group<*>>): String {
            return SQL(groups).replace("\"", "")
        }
    }
}