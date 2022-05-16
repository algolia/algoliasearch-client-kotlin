package com.algolia.search.model.filter

import com.algolia.search.model.filter.internal.Converter
import com.algolia.search.model.filter.internal.toLegacy
import com.algolia.search.model.filter.internal.toSQL

/**
 * Converts a single [Filter] to a type [O].
 */
public sealed class FilterConverter<I : Filter, O> : Converter<I, O> {

    /**
     * Converts a [Filter] to its SQL-like [String] representation.
     */
    public object SQL : FilterConverter<Filter, String>() {

        override fun invoke(input: Filter): String {
            return when (input) {
                is Filter.Facet -> input.toSQL()
                is Filter.Tag -> input.toSQL()
                is Filter.Numeric -> input.toSQL()
            }
        }
    }

    /**
     * Converts a [Filter] to its legacy representation.
     */
    public object Legacy : FilterConverter<Filter, List<String>>() {

        override fun invoke(input: Filter): List<String> {
            return toLegacy(input, escape = true)
        }

        /**
         * Same as [Legacy], but without quotes.
         */
        public object Unquoted : FilterConverter<Filter, List<String>>() {

            override fun invoke(input: Filter): List<String> {
                return toLegacy(input, escape = false)
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
