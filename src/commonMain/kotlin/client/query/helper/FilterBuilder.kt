package client.query.helper


/**
 * For a better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
 */
@QueryHelper
class FilterBuilder {

    private val filters = Filters<Filter>()

    /**
     * @param filters One or many [Filter].
     *
     * Add one or several AND (conjunctive) [Filter] to the [filters] list.
     * Conjunctive filters will result in a expression such as this: FilterA AND FilterB AND ...
     */
    fun and(vararg filters: Filter): FilterBuilder {
        this.filters.and(*filters)
        return this
    }

    /**
     * @param filters One or many [Filter].
     * Add one or several OR (disjunctive) [Filter] to the [filters] list.
     * Disjunctive filters will be grouped by [Filter.attribute] in a expression such as this:
     * (attributeA:valueA OR attributeA:valueB) AND (attributeB:valueA OR attributeB:valueB)
     * If a disjunctive [Filter] is added, but the list of disjunctive filters already contains a [Filter] with the same
     * [Filter.attribute] but has an incompatible type, an [IncompatibleFilterTypeException] will be thrown.
     * [FilterFacet] and [FilterBoolean] are compatibles, which is represented by the [FacetFilter] class.
     * [FilterRange] and [FilterComparison] are compatibles, which is represented by the [NumericFilter] class.
     * [FacetFilter] and [NumericFilter] with the same [Attribute] are not compatibles.
     */
    fun or(vararg filters: Filter): FilterBuilder {
        this.filters.or(*filters)
        return this
    }

    /**
     * @param filter The [Filter] to replace.
     * @param replacement The [Filter] to replace it with.
     *
     * Replace all occurrences of [filter] by [replacement], whether disjunctive or conjunctive.
     */
    fun replace(filter: Filter, replacement: Filter): FilterBuilder {
        filters.replace(filter, replacement)
        return this
    }

    /**
     * @param filters The [Filter] to remove.
     *
     * Remove all occurrences of [filters], whether disjunctive or conjunctive.
     */
    fun remove(vararg filters: Filter): FilterBuilder {
        this.filters.remove(*filters)
        return this
    }

    /**
     * @param attribute The [Attribute] used for matching, if any.
     *
     * Retrieve all [Filter] matching the [attribute], if any. Returns conjunctive and disjunctive
     * filters indifferently.
     */
    fun getFilters(attribute: Attribute? = null): Set<Filter> {
        return filters.getFilters(attribute)
    }

    /**
     * @param attribute The [Attribute]] matching [Filter.attribute], if any.
     *
     * Remove all [Filter], whether disjunctive or conjunctive.
     * You can specify a [attribute] to only remove [Filter] that matches.
     */
    fun clear(attribute: Attribute? = null): FilterBuilder {
        filters.clear(attribute)
        return this
    }

    /**
     * @param attribute The [Attribute] to replace.
     * @param replacement The [Attribute] to replacement.
     *
     * Replace all [Filter.attribute] matching [attribute] by [replacement].
     */
    fun replaceAttribute(attribute: Attribute, replacement: Attribute): FilterBuilder {
        filters.replaceAttribute(attribute, replacement)
        return this
    }

    /**
     * @return The SQL-like syntax represented by a [String]
     *
     * Build the [filters] into a SQL-like syntax.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
     */
    fun build(): String {
        val ands = filters.ands
            .map { listOf(it) }
        val ors = filters.ors
            .groupBy { it.attribute }
            .map { it.value }
        val filters = ands + ors

        return filters.joinToString(separator = " AND ") { group ->
            val condition = group.size == 1 || (ands.isEmpty() && ors.size == 1)
            val prefix = if (condition) "" else "("
            val postfix = if (condition) "" else ")"

            group.joinToString(prefix = prefix, postfix = postfix, separator = " OR ") {
                it.build()
            }
        }
    }
}