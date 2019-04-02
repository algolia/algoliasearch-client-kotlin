package com.algolia.search.dsl.filtering


public sealed class Group<T : Filter> : Set<T> {

    abstract val filters: Set<T>

    public data class And<T : Filter>(override val filters: Set<T>) : Group<T>(), Set<T> by filters {

        public constructor(vararg filters: T) : this(filters.toSet())
    }

    public data class Or<T : Filter>(override val filters: Set<T>) : Group<T>(), Set<T> by filters {

        public constructor(vararg filters: T) : this(filters.toSet())
    }
}