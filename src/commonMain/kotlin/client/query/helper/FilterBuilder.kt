package client.query.helper

/**
 * For better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
 */
class FilterBuilder {

    /**
     * To represent our SQL-like syntax of filters, we use a nested array of [Filter].
     * Each nested MutableList<Filter> represents a group. If this group contains two or more elements,
     * it will be considered  as a disjunctive group (Operator OR).
     * The operator AND will be used between each nested group.
     *
     * Example:
     *
     * ((FilterA), (FilterB), (FilterC, FilterD), (FilterE, FilterF))
     *
     * will give us the following SQL-like expression:
     *
     * FilterA AND FilterB AND (FilterC OR FilterD) AND (FilterE OR FilterF)
     */
    private val filters = mutableListOf<MutableList<Filter>>()

    /**
     * @param filter One or many [Filter].
     *
     * Add one or several conjunctive [Filter] to the [filters] list.
     * Adding several filters will result in the following expression: FilterA AND FilterB AND ...
     */
    fun and(vararg filter: Filter): FilterBuilder {
        filters.and(*filter)
        return this
    }

    /**
     * @param first The first [FilterFacet].
     * @param second the second [FilterFacet].
     * @param filter Between 0 and N other [FilterFacet].
     *
     * Add at least two [FilterFacet] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun or(first: FilterFacet, second: FilterFacet, vararg filter: FilterFacet): FilterBuilder {
        filters.or(first, second, *filter)
        return this
    }

    /**
     * @param first The first [FilterFacet].
     * @param second the second [FilterBoolean].
     * @param filter Between 0 and N other [FilterBoolean].
     *
     * Add at least two [FilterBoolean] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun or(first: FilterBoolean, second: FilterBoolean, vararg filter: FilterBoolean): FilterBuilder {
        filters.or(first, second, *filter)
        return this
    }

    /**
     * @param first The first [FilterTag].
     * @param second the second [FilterTag].
     * @param filter Between 0 and N other [FilterTag].
     *
     * Add at least two [FilterTag] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun or(first: FilterTag, second: FilterTag, vararg filter: FilterTag): FilterBuilder {
        filters.or(first, second, *filter)
        return this
    }

    /**
     * @param first The first [FilterComparison].
     * @param second the second [FilterComparison].
     * @param filter Between 0 and N other [FilterComparison].
     *
     * Add at least two [FilterComparison] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun or(
        first: FilterComparison,
        second: FilterComparison,
        vararg filter: FilterComparison
    ): FilterBuilder {
        filters.or(first, second, *filter)
        return this
    }

    /**
     * @param first The first [FilterRange].
     * @param second the second [FilterRange].
     * @param filter Between 0 and N other [FilterRange].
     *
     * Add at least two [FilterRange] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun or(first: FilterRange, second: FilterRange, vararg filter: FilterRange): FilterBuilder {
        filters.or(first, second, *filter)
        return this
    }

    /**
     * @param filter The [FilterFacet] that will be replaced.
     * @param replacement The [FilterFacet] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: FilterFacet, replacement: FilterFacet): FilterBuilder {
        filters.replace(filter, replacement)
        return this
    }

    /**
     * @param filter The [FilterBoolean] that will be replaced.
     * @param replacement The [FilterBoolean] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: FilterBoolean, replacement: FilterBoolean): FilterBuilder {
        filters.replace(filter, replacement)
        return this
    }

    /**
     * @param filter The [FilterTag] that will be replaced.
     * @param replacement The [FilterTag] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: FilterTag, replacement: FilterTag): FilterBuilder {
        filters.replace(filter, replacement)
        return this
    }

    /**
     * @param filter The [FilterComparison] that will be replaced.
     * @param replacement The [FilterComparison] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: FilterComparison, replacement: FilterComparison): FilterBuilder {
        filters.replace(filter, replacement)
        return this
    }

    /**
     * @param filter The [FilterRange] that will be replaced.
     * @param replacement The [FilterRange] used as a replacement.
     *
     * Search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: FilterRange, replacement: FilterRange): FilterBuilder {
        filters.replace(filter, replacement)
        return this
    }

    /**
     * @param filter The [Filter] to remove.
     *
     * Remove all occurrences of [filter] inside the [filters] list.
     */
    fun remove(vararg filter: Filter): FilterBuilder {
        filters.remove(*filter)
        return this
    }

    /**
     * @param attribute The [Filter.attribute] used for matching.
     *
     * Retrieve all [Filter] in the [filters] list matching the [attribute].
     */
    fun getFilters(attribute: Attribute): List<Filter> {
        return filters.getFilters(attribute)
    }

    /**
     * @param attribute The attribute matching [Filter.attribute].
     *
     * Remove all [OptionalFilter] in [filters].
     * You can specify a [attribute] to only remove [OptionalFilter] that matches.
     */
    fun clear(attribute: Attribute? = null): FilterBuilder {
        filters.clear(attribute)
        return this
    }

    /**
     * @param attribute The attribute matching [Filter.attribute].
     * @param replacement Value used to replace the attribute that matched.
     *
     * Use this method to replace all [Filter] in the [filters] list which have the same [Filter.attribute]
     * as the specified [attribute] with the [replacement].
     *
     * Example:
     *
     * ```
     * val helper = FilterBuilder()
     *
     * val filterA = FilterFacet("attributeA", "valueA", "groupA")
     * val filterB = FilterFacet("attributeA", "valueB", "groupB")
     *
     * helper.and(filterA, filterB)
     * assertEquals("attributeA:valueA AND attributeA:valueB", helper.build())
     *
     * helper.replaceAttribute(attribute = "attributeA", replacement = "attributeC", group = "groupA")
     * assertEquals("attributeC:valueA", "attributeA:valueB", helper.build())
     *
     * ```
     *
     * As you can see, only the filter with "groupA" was replaced, despite both filters having "attributeA" marked to
     * be replaced by "attributeC".
     * In this example, if no group would have been specified (group = null), both filters would have been affected.
     */
    fun replaceAttribute(attribute: Attribute, replacement: Attribute): FilterBuilder {
        filters.replaceAttribute(attribute, replacement)
        return this
    }

    /**
     * @return The SQL-like syntax represented by a [String]
     *
     * Build the [filters] list into a SQL-like syntax.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
     */
    fun build(): String {
        return filters.joinToString(separator = " AND ") { group ->
            val prefix = if (group.size == 1 || filters.size == 1) "" else "("
            val postfix = if (group.size == 1 || filters.size == 1) "" else ")"

            group.joinToString(prefix = prefix, postfix = postfix, separator = " OR ") {
                it.build()
            }
        }
    }
}