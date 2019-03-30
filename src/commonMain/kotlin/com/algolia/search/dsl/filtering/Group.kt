package com.algolia.search.dsl.filtering


public sealed class Group<T : Filter> {

    abstract val filters: Set<T>
}

public sealed class GroupAnd<T : Filter> : Group<T>() {

    public data class All(override val filters: Set<Filter>) : GroupAnd<Filter>()

    public data class Facet(override val filters: Set<FilterFacet>) : GroupAnd<FilterFacet>()

    public data class Tag(override val filters: Set<FilterTag>) : GroupAnd<FilterTag>()

    public data class Numeric(override val filters: Set<FilterNumeric>) : GroupAnd<FilterNumeric>()
}

public sealed class GroupOr<T : Filter> : Group<T>() {

    public data class Facet(override val filters: Set<FilterFacet>) : GroupOr<FilterFacet>()

    public data class Tag(override val filters: Set<FilterTag>) : GroupOr<FilterTag>()

    public data class Numeric(override val filters: Set<FilterNumeric>) : GroupOr<FilterNumeric>()
}