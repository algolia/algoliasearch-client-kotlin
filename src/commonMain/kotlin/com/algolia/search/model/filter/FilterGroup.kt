package com.algolia.search.model.filter


public sealed class FilterGroup<T : Filter> : Set<T> {

    protected abstract val filters: Set<T>

    public abstract val name: String?

    public sealed class And<T : Filter>(
        override val filters: Set<T>,
        override val name: String?
    ) : FilterGroup<T>(),
        Set<T> by filters {

        public data class Any(
            override val filters: Set<Filter>,
            override val name: String? = null
        ) : And<Filter>(filters, name) {

            constructor(vararg filters: Filter, name: String? = null) : this(filters.toSet(), name)
        }

        public data class Facet(
            override val filters: Set<Filter.Facet>,
            override val name: String? = null
        ) : And<Filter.Facet>(filters, name) {

            constructor(vararg filters: Filter.Facet, name: String? = null) : this(filters.toSet(), name)
        }

        public data class Tag(
            override val filters: Set<Filter.Tag>,
            override val name: String? = null
        ) : And<Filter.Tag>(filters, name) {

            constructor(vararg filters: Filter.Tag, name: String? = null) : this(filters.toSet(), name)
        }

        public data class Numeric(
            override val filters: Set<Filter.Numeric>,
            override val name: String? = null
        ) : And<Filter.Numeric>(filters, name) {

            constructor(vararg filters: Filter.Numeric, name: String? = null) : this(filters.toSet(), name)
        }
    }

    public sealed class Or<T : Filter>(
        override val filters: Set<T>,
        override val name: String?
    ) : FilterGroup<T>(), Set<T> by filters {

        public data class Facet(
            override val filters: Set<Filter.Facet>,
            override val name: String? = null
        ) : Or<Filter.Facet>(filters, name) {

            constructor(vararg filters: Filter.Facet, name: String? = null) : this(filters.toSet(), name)
        }

        public data class Tag(
            override val filters: Set<Filter.Tag>,
            override val name: String? = null
        ) : Or<Filter.Tag>(filters, name) {

            constructor(vararg filters: Filter.Tag, name: String? = null) : this(filters.toSet(), name)
        }

        public data class Numeric(
            override val filters: Set<Filter.Numeric>,
            override val name: String? = null
        ) : Or<Filter.Numeric>(filters, name) {

            constructor(vararg filters: Filter.Numeric, name: String? = null) : this(filters.toSet(), name)
        }
    }
}