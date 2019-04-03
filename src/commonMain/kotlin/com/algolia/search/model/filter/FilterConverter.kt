package com.algolia.search.model.filter


public sealed class FilterConverter<I, O> : (I) -> O {

    public object SQL : FilterConverter<Filter, String>() {

        override fun invoke(filter: Filter): String {
            return when (filter) {
                is Filter.Facet -> filter.toSQL()
                is Filter.Tag -> filter.toSQL()
                is Filter.Numeric -> filter.toSQL()
            }
        }
    }

    public object Legacy : FilterConverter<Filter, List<String>>() {

        override fun invoke(filter: Filter): List<String> {
            return when (filter) {
                is Filter.Facet -> filter.toLegacy()
                is Filter.Tag -> filter.toLegacy()
                is Filter.Numeric -> filter.toLegacy()
            }
        }
    }
}