package client.query.helper


class OptionalFilterBuilder {

    /**
     * To represent our SQL-like syntax of filters, we use a nested array of [OptionalFilter].
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
    private val filters = mutableListOf<MutableList<OptionalFilter>>()

    /**
     * @param filter One or many [OptionalFilter].
     *
     * Add one or several conjunctive [OptionalFilter] to the [filters] list.
     * Adding several filters will result in the following expression: FilterA AND FilterB AND ...
     */
    fun and(vararg filter: OptionalFilter): OptionalFilterBuilder {
        filters.and(*filter)
        return this
    }

    /**
     * @param filter One or many [OptionalFilter].
     */
    fun or(vararg filter: OptionalFilter): OptionalFilterBuilder {
        filters.or(*filter)
        return this
    }

    /**
     * @param filter The [OptionalFilter] that will be replaced.
     * @param replacement The [OptionalFilter] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: OptionalFilter, replacement: OptionalFilter): OptionalFilterBuilder {
        filters.replace(filter, replacement)
        return this
    }

    /**
     * @param filter The [OptionalFilter] to remove.
     *
     * Remove all occurrences of [filter] inside the [filters] list.
     */
    fun remove(vararg filter: OptionalFilter): OptionalFilterBuilder {
        filters.remove(*filter)
        return this
    }

    /**
     * @param attribute The [Filter.attribute] used for matching.
     *
     * Retrieve all [OptionalFilter] in the [filters] list matching the [attribute].
     */
    fun getFilters(attribute: Attribute): List<OptionalFilter> {
        return filters.getFilters(attribute)
    }

    /**
     * @param attribute The attribute matching [Filter.attribute].
     *
     * Remove all [OptionalFilter] in [filters].
     * You can specify a [attribute] to only remove [OptionalFilter] that matches.
     */
    fun clear(attribute: Attribute? = null): OptionalFilterBuilder {
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
    fun replaceAttribute(
        attribute: Attribute,
        replacement: Attribute
    ): OptionalFilterBuilder {
        filters.replaceAttribute(attribute, replacement)
        return this
    }

    fun build(): List<List<String>> {
        return filters.map { it.map { it.expression } }
    }
}