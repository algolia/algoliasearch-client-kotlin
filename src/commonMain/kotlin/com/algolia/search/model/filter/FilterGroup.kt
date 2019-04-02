package com.algolia.search.model.filter


public sealed class FilterGroup<T : Filter> : Set<T> {

    protected abstract val filters: Set<T>

    public data class And<T : Filter>(override val filters: Set<T>) : FilterGroup<T>(), Set<T> by filters {

        public constructor(vararg filters: T) : this(filters.toSet())
    }

    public data class Or<T : Filter>(override val filters: Set<T>) : FilterGroup<T>(), Set<T> by filters {

        public constructor(vararg filters: T) : this(filters.toSet())
    }
}