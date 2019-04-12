package com.algolia.search.model.filter


public sealed class FilterGroup<T : Filter> : Set<T> {

    protected abstract val filters: Set<T>

    public sealed class And<T : Filter>(override val filters: Set<T>) : FilterGroup<T>(), Set<T> by filters {

        data class Any(override val filters: Set<Filter>) : And<Filter>(filters) {

            constructor(vararg filters: Filter) : this(filters.toSet())
        }

        data class Facet(override val filters: Set<Filter.Facet>) : And<Filter.Facet>(filters) {

            constructor(vararg filters: Filter.Facet) : this(filters.toSet())
        }

        data class Tag(override val filters: Set<Filter.Tag>) : And<Filter.Tag>(filters) {

            constructor(vararg filters: Filter.Tag) : this(filters.toSet())
        }

        data class Numeric(override val filters: Set<Filter.Numeric>) : And<Filter.Numeric>(filters) {

            constructor(vararg filters: Filter.Numeric) : this(filters.toSet())
        }
    }

    public sealed class Or<T : Filter>(override val filters: Set<T>) : FilterGroup<T>(), Set<T> by filters {

        data class Facet(override val filters: Set<Filter.Facet>) : Or<Filter.Facet>(filters) {

            constructor(vararg filters: Filter.Facet) : this(filters.toSet())
        }

        data class Tag(override val filters: Set<Filter.Tag>) : Or<Filter.Tag>(filters) {

            constructor(vararg filters: Filter.Tag) : this(filters.toSet())
        }

        data class Numeric(override val filters: Set<Filter.Numeric>) : Or<Filter.Numeric>(filters) {

            constructor(vararg filters: Filter.Numeric) : this(filters.toSet())
        }
    }
}