package com.algolia.search.dsl.filtering


public sealed class Group<T : Filter> : Set<T> {

    abstract val filters: Set<T>
}

public sealed class GroupAnd<T : Filter> : Group<T>() {

    public data class All(
        override val filters: Set<Filter>
    ) : GroupAnd<Filter>(),
        Set<Filter> by filters {

        public constructor(vararg filters: Filter) : this(filters.toSet())
    }

    public data class Facet(
        override val filters: Set<Filter.Facet>
    ) : GroupAnd<Filter.Facet>(),
        Set<Filter.Facet> by filters {

        public constructor(vararg filters: Filter.Facet) : this(filters.toSet())
    }

    public data class Tag(
        override val filters: Set<Filter.Tag>
    ) : GroupAnd<Filter.Tag>(),
        Set<Filter.Tag> by filters {

        public constructor(vararg filters: Filter.Tag) : this(filters.toSet())
    }

    public data class Numeric(
        override val filters: Set<Filter.Numeric>
    ) : GroupAnd<Filter.Numeric>(),
        Set<Filter.Numeric> by filters {

        public constructor(vararg filters: Filter.Numeric) : this(filters.toSet())
    }
}

public sealed class GroupOr<T : Filter> : Group<T>() {

    public data class Facet(
        override val filters: Set<Filter.Facet>
    ) : GroupOr<Filter.Facet>(),
        Set<Filter.Facet> by filters {

        public constructor(vararg filters: Filter.Facet) : this(filters.toSet())
    }

    public data class Tag(
        override val filters: Set<Filter.Tag>
    ) : GroupOr<Filter.Tag>(),
        Set<Filter.Tag> by filters {

        public constructor(vararg filters: Filter.Tag) : this(filters.toSet())
    }

    public data class Numeric(
        override val filters: Set<Filter.Numeric>
    ) : GroupOr<Filter.Numeric>(),
        Set<Filter.Numeric> by filters {

        public constructor(vararg filters: Filter.Numeric) : this(filters.toSet())
    }
}