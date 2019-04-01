package com.algolia.search.dsl.filtering


public sealed class Group<T : Filter> : MutableSet<T> {

    abstract val filters: MutableSet<T>
}

public sealed class GroupAnd<T : Filter> : Group<T>() {

    public data class All(
        override val filters: MutableSet<Filter>
    ) : GroupAnd<Filter>(),
        MutableSet<Filter> by filters

    public data class Facet(
        override val filters: MutableSet<FilterFacet>
    ) : GroupAnd<FilterFacet>(),
        MutableSet<FilterFacet> by filters

    public data class Tag(
        override val filters: MutableSet<FilterTag>
    ) : GroupAnd<FilterTag>(),
        MutableSet<FilterTag> by filters

    public data class Numeric(
        override val filters: MutableSet<FilterNumeric>
    ) : GroupAnd<FilterNumeric>(),
        MutableSet<FilterNumeric> by filters
}

public sealed class GroupOr<T : Filter> : Group<T>() {

    public data class Facet(
        override val filters: MutableSet<FilterFacet>
    ) : GroupOr<FilterFacet>(),
        MutableSet<FilterFacet> by filters

    public data class Tag(
        override val filters: MutableSet<FilterTag>
    ) : GroupOr<FilterTag>(),
        MutableSet<FilterTag> by filters

    public data class Numeric(
        override val filters: MutableSet<FilterNumeric>
    ) : GroupOr<FilterNumeric>(),
        MutableSet<FilterNumeric> by filters
}