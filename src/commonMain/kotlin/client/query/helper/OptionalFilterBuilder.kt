package client.query.helper


/**
 * For a better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/]
 */
@QueryHelper
class OptionalFilterBuilder {


    private val filters = Filters<FacetFilter>()

    /**
     * @param filters One or many [FacetFilter].
     *
     * Add one or several AND (conjunctive) [FacetFilter] to the [filters] list.
     * Conjunctive filters will result in a expression such as this: FilterA AND FilterB AND ...
     */
    fun and(vararg filters: FacetFilter): OptionalFilterBuilder {
        this.filters.and(*filters)
        return this
    }

    /**
     * @param filters One or many [FacetFilter].
     * Add one or several OR (disjunctive) [FacetFilter] to the [filters] list.
     * Disjunctive filters will be grouped by [FacetFilter.attribute] in a expression such as this:
     * (attributeA:valueA OR attributeA:valueB) AND (attributeB:valueA OR attributeB:valueB)
     */
    fun or(vararg filters: FacetFilter): OptionalFilterBuilder {
        this.filters.or(*filters)
        return this
    }

    /**
     * @param filter The [FacetFilter] to replace.
     * @param replacement The [FacetFilter] to replace it with.
     *
     * Replace all occurrences of [filter] by [replacement], whether disjunctive or conjunctive.
     */
    fun replace(filter: FacetFilter, replacement: FacetFilter): OptionalFilterBuilder {
        filters.replace(filter, replacement)
        return this
    }

    /**
     * @param filters The [FacetFilter] to remove.
     *
     * Remove all occurrences of [filters], whether disjunctive or conjunctive.
     */
    fun remove(vararg filters: FacetFilter): OptionalFilterBuilder {
        this.filters.remove(*filters)
        return this
    }

    /**
     * @param attribute The [Attribute] used for matching, if any.
     *
     * Retrieve all [FacetFilter] matching the [attribute], if any. Returns conjunctive and disjunctive
     * filters indifferently.
     */
    fun getFilters(attribute: Attribute? = null): Set<FacetFilter> {
        return filters.getFilters(attribute)
    }

    /**
     * @param attribute The [Attribute]] matching [FacetFilter.attribute], if any.
     *
     * Remove all [FacetFilter], whether disjunctive or conjunctive.
     * You can specify a [attribute] to only remove [FacetFilter] that matches.
     */
    fun clear(attribute: Attribute? = null): OptionalFilterBuilder {
        filters.clear(attribute)
        return this
    }

    /**
     * @param attribute The [Attribute] to replace.
     * @param replacement The [Attribute] to replacement.
     *
     * Replace all [FacetFilter.attribute] matching [attribute] by [replacement].
     */
    fun replaceAttribute(
        attribute: Attribute,
        replacement: Attribute
    ): OptionalFilterBuilder {
        filters.replaceAttribute(attribute, replacement)
        return this
    }

    /**
     * Build the [filters] into a nested list of strings.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/]
     */
    fun build(): List<List<String>> {
        val ands = filters.ands.map { it.expression }.map { listOf(it) }
        val ors = filters.ors.groupBy { it.attribute }.map { it.value.map { it.expression } }

        return ands + ors
    }
}