package com.algolia.search.model.filter

import com.algolia.search.model.Attribute

/**
 * Contains a [Set] of [Filter] that should be evaluated together.
 */
sealed class FilterGroup<T : Filter> : Set<T> {

    protected abstract val filters: Set<T>

    abstract val name: String?

    /**
     * Filters in this group will be evaluated together with the "AND" operator.
     */
    sealed class And<T : Filter>(
        override val filters: Set<T>,
        override val name: String?
    ) : FilterGroup<T>(),
        Set<T> by filters {

        /**
         * Contains any type of [Filter].
         */
        data class Any(
            override val filters: Set<Filter>,
            override val name: String? = null
        ) : And<Filter>(filters, name) {

            constructor(vararg filters: Filter, name: String? = null) : this(filters.toSet(), name)
        }

        /**
         * Contains only [Filter.Facet].
         */
        data class Facet(
            override val filters: Set<Filter.Facet>,
            override val name: String? = null
        ) : And<Filter.Facet>(filters, name) {

            constructor(vararg filters: Filter.Facet, name: String? = null) : this(filters.toSet(), name)
        }

        /**
         * Contains only [Filter.Tag].
         */
        data class Tag(
            override val filters: Set<Filter.Tag>,
            override val name: String? = null
        ) : And<Filter.Tag>(filters, name) {

            constructor(vararg filters: Filter.Tag, name: String? = null) : this(filters.toSet(), name)
        }

        /**
         * Contains only [Filter.Numeric].
         */
        data class Numeric(
            override val filters: Set<Filter.Numeric>,
            override val name: String? = null
        ) : And<Filter.Numeric>(filters, name) {

            constructor(vararg filters: Filter.Numeric, name: String? = null) : this(filters.toSet(), name)
        }

        data class Hierarchical(
            override val filters: Set<Filter.Facet>,
            val path: List<Filter.Facet>,
            val attributes: List<Attribute>,
            override val name: String? = null
        ) : And<Filter.Facet>(filters, name)
    }

    /**
     * Filters in this group will be evaluated together with the "OR" operator.
     */
    sealed class Or<T : Filter>(
        override val filters: Set<T>,
        override val name: String?
    ) : FilterGroup<T>(), Set<T> by filters {

        /**
         * Contains only [Filter.Facet].
         */
        data class Facet(
            override val filters: Set<Filter.Facet>,
            override val name: String? = null
        ) : Or<Filter.Facet>(filters, name) {

            constructor(vararg filters: Filter.Facet, name: String? = null) : this(filters.toSet(), name)
        }

        /**
         * Contains only [Filter.Tag].
         */
        data class Tag(
            override val filters: Set<Filter.Tag>,
            override val name: String? = null
        ) : Or<Filter.Tag>(filters, name) {

            constructor(vararg filters: Filter.Tag, name: String? = null) : this(filters.toSet(), name)
        }

        /**
         * Contains only [Filter.Numeric].
         */
        data class Numeric(
            override val filters: Set<Filter.Numeric>,
            override val name: String? = null
        ) : Or<Filter.Numeric>(filters, name) {

            constructor(vararg filters: Filter.Numeric, name: String? = null) : this(filters.toSet(), name)
        }
    }
}
