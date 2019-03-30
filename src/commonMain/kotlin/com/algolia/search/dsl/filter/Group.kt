package com.algolia.search.dsl.filter


public sealed class Group<T : Filter> {

    abstract val filters: Set<T>
}

public data class GroupAnd(override val filters: Set<Filter>) : Group<Filter>()

public sealed class GroupOr<T : Filter> : Group<T>() {

    public data class Facet(override val filters: Set<FilterFacet>) : GroupOr<FilterFacet>()

    public data class Tag(override val filters: Set<FilterTag>) : GroupOr<FilterTag>()

    public data class Numeric(override val filters: Set<FilterNumeric>) : GroupOr<FilterNumeric>()
}

public data class GroupAndFacet(override val filters: Set<FilterFacet>) : Group<FilterFacet>()