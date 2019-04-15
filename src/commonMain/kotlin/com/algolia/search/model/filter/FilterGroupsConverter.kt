package com.algolia.search.model.filter


public sealed class FilterGroupsConverter<I, O> : (I) -> O {

    public object SQL : FilterGroupsConverter<List<FilterGroup<*>>, String?>() {

        override fun invoke(groups: List<FilterGroup<*>>): String? {
            return if (groups.isNotEmpty()) {
                groups.joinToString(separator = " AND ") { group ->
                    val separator = when (group) {
                        is FilterGroup.And -> " AND "
                        is FilterGroup.Or -> " OR "
                    }
                    group.joinToString(prefix = "(", postfix = ")", separator = separator) { FilterConverter.SQL(it) }
                }
            } else null
        }

        public object Unquoted : FilterGroupsConverter<List<FilterGroup<*>>, String?>() {

            override fun invoke(groups: List<FilterGroup<*>>): String? {
                return SQL(groups)?.replace("\"", "")
            }
        }
    }

    public sealed class Legacy<T : Filter> : FilterGroupsConverter<List<FilterGroup<T>>, List<List<String>>>() {

        override fun invoke(groups: List<FilterGroup<T>>): List<List<String>> {
            val (andEntries, orEntries) = groups.partition { it is FilterGroup.And }
            val ands =
                andEntries.flatMap { group -> group.flatMap { FilterConverter.Legacy(it) } }.map { listOf(it) }
            val ors = orEntries.map { group -> group.flatMap { FilterConverter.Legacy(it) } }

            return ands + ors
        }

        object Facet : Legacy<Filter.Facet>()

        object Tag : Legacy<Filter.Tag>()

        object Numeric : Legacy<Filter.Numeric>()
    }
}