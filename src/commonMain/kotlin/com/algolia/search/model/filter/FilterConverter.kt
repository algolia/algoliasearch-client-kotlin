package com.algolia.search.model.filter

/**
 * Converts a single [Filter] to a type [O].
 */
public sealed class FilterConverter<I : Filter, O> : (I) -> O {

    /**
     * Converts a [Filter] to its SQL-like [String] representation.
     */
    public object SQL : FilterConverter<Filter, String>() {

        override fun invoke(filter: Filter): String {
            return when (filter) {
                is Filter.Facet -> filter.toSQL()
                is Filter.Tag -> filter.toSQL()
                is Filter.Numeric -> filter.toSQL()
            }
        }
    }

    /**
     * Converts a [Filter] to its legacy representation.
     */
    public object Legacy : FilterConverter<Filter, List<String>>() {

        override fun invoke(filter: Filter): List<String> {
            return toLegacy(filter, escape = false)
        }

        /**
         * Same as [Legacy], but without quotes.
         */
        public object Unquoted : FilterConverter<Filter, List<String>>() {

            override fun invoke(filter: Filter): List<String> {
                return toLegacy(filter, escape = true)
            }
        }

        private fun toLegacy(filter: Filter, escape: Boolean): List<String> {
            return when (filter) {
                is Filter.Facet -> filter.toLegacy(escape)
                is Filter.Tag -> filter.toLegacy(escape)
                is Filter.Numeric -> filter.toLegacy(escape)
            }
        }
    }
}
