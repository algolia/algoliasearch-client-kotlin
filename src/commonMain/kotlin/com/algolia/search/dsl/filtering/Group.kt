package com.algolia.search.dsl.filtering


public sealed class Group<T : Filter> : MutableSet<T> {

    abstract val filters: MutableSet<T>
}

public sealed class GroupAnd<T : Filter> : Group<T>() {

    public data class All(
        override val filters: MutableSet<Filter>
    ) : GroupAnd<Filter>(),
        MutableSet<Filter> by filters {

        public constructor(vararg filters: Filter) : this(filters.toMutableSet())
    }

    public data class Facet(
        override val filters: MutableSet<Filter.Facet>
    ) : GroupAnd<Filter.Facet>(),
        MutableSet<Filter.Facet> by filters {

        public constructor(vararg filters: Filter.Facet) : this(filters.toMutableSet())
    }

    public data class Tag(
        override val filters: MutableSet<Filter.Tag>
    ) : GroupAnd<Filter.Tag>(),
        MutableSet<Filter.Tag> by filters {

        public constructor(vararg filters: Filter.Tag) : this(filters.toMutableSet())
    }

    public data class Numeric(
        override val filters: MutableSet<Filter.Numeric>
    ) : GroupAnd<Filter.Numeric>(),
        MutableSet<Filter.Numeric> by filters {

        public constructor(vararg filters: Filter.Numeric) : this(filters.toMutableSet())
    }
}

public sealed class GroupOr<T : Filter> : Group<T>() {

    public data class Facet(
        override val filters: MutableSet<Filter.Facet>
    ) : GroupOr<Filter.Facet>(),
        MutableSet<Filter.Facet> by filters {

        public constructor(vararg filters: Filter.Facet) : this(filters.toMutableSet())
    }

    public data class Tag(
        override val filters: MutableSet<Filter.Tag>
    ) : GroupOr<Filter.Tag>(),
        MutableSet<Filter.Tag> by filters {

        public constructor(vararg filters: Filter.Tag) : this(filters.toMutableSet())
    }

    public data class Numeric(
        override val filters: MutableSet<Filter.Numeric>
    ) : GroupOr<Filter.Numeric>(),
        MutableSet<Filter.Numeric> by filters {

        public constructor(vararg filters: Filter.Numeric) : this(filters.toMutableSet())
    }
}