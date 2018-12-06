package client.query.helper

/**
 * For better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
 */
class FilterBuilder {

    /**
     *
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
        filter.forEach {
            filters += mutableListOf(it)
        }
        return this
    }

    /**
     * You can only create a disjunctive group of filters with exactly the same [Filter] type.
     * Public methods [or] with the same [Filter] parameters in the method signature enforce this rule.
     */
    private fun orInternal(vararg filter: Filter): FilterBuilder {
        filters += mutableListOf(*filter)
        return this
    }

    /**
     * @param first The first [Filter.Facet].
     * @param second the second [Filter.Facet].
     * @param filter Between 0 and N other [Filter.Facet].
     *
     * Add at least two [Filter.Facet] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun or(first: Filter.Facet, second: Filter.Facet, vararg filter: Filter.Facet): FilterBuilder {
        return orInternal(first, second, *filter)
    }

    /**
     * @param first The first [Filter.Facet].
     * @param second the second [Filter.Boolean].
     * @param filter Between 0 and N other [Filter.Boolean].
     *
     * Add at least two [Filter.Boolean] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun or(first: Filter.Boolean, second: Filter.Boolean, vararg filter: Filter.Boolean): FilterBuilder {
        return orInternal(first, second, *filter)
    }

    /**
     * @param first The first [Filter.Tag].
     * @param second the second [Filter.Tag].
     * @param filter Between 0 and N other [Filter.Tag].
     *
     * Add at least two [Filter.Tag] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun or(first: Filter.Tag, second: Filter.Range, vararg filter: Filter.Tag): FilterBuilder {
        return orInternal(first, second, *filter)
    }

    /**
     * @param first The first [Filter.Comparison].
     * @param second the second [Filter.Comparison].
     * @param filter Between 0 and N other [Filter.Comparison].
     *
     * Add at least two [Filter.Comparison] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun or(
        first: Filter.Comparison,
        second: Filter.Comparison,
        vararg filter: Filter.Comparison
    ): FilterBuilder {
        return orInternal(first, second, *filter)
    }

    /**
     * @param first The first [Filter.Range].
     * @param second the second [Filter.Range].
     * @param filter Between 0 and N other [Filter.Range].
     *
     * Add at least two [Filter.Range] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun or(first: Filter.Range, second: Filter.Range, vararg filter: Filter.Range): FilterBuilder {
        return orInternal(first, second, *filter)
    }

    /**
     * You can only replace a [Filter] by another [Filter] of exactly the same type.
     * Public methods [replace] with the same [Filter] parameters in the method signature enforce this rule.
     */
    private fun replaceInternal(filter: Filter, replacement: Filter): FilterBuilder {
        filters.forEach { filters ->
            val index = filters.indexOf(filter)

            if (index != -1) {
                filters.removeAt(index)
                filters.add(index, replacement)
            }
        }
        return this
    }

    /**
     * @param filter The [Filter.Facet] that will be replaced.
     * @param replacement The [Filter.Facet] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: Filter.Facet, replacement: Filter.Facet): FilterBuilder {
        return replaceInternal(filter, replacement)
    }

    /**
     * @param filter The [Filter.Boolean] that will be replaced.
     * @param replacement The [Filter.Boolean] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: Filter.Boolean, replacement: Filter.Boolean): FilterBuilder {
        return replaceInternal(filter, replacement)
    }

    /**
     * @param filter The [Filter.Tag] that will be replaced.
     * @param replacement The [Filter.Tag] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: Filter.Tag, replacement: Filter.Tag): FilterBuilder {
        return replaceInternal(filter, replacement)
    }

    /**
     * @param filter The [Filter.Comparison] that will be replaced.
     * @param replacement The [Filter.Comparison] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: Filter.Comparison, replacement: Filter.Comparison): FilterBuilder {
        return replaceInternal(filter, replacement)
    }

    /**
     * @param filter The [Filter.Range] that will be replaced.
     * @param replacement The [Filter.Range] used as a replacement.
     *
     * Search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: Filter.Range, replacement: Filter.Range): FilterBuilder {
        return replaceInternal(filter, replacement)
    }

    /**
     * @param filter The [Filter] to remove.
     *
     * Remove all occurrences of [filter] inside the [filters] list.
     */
    fun remove(vararg filter: Filter): FilterBuilder {
        filter.forEach {
            filters.forEach { filters -> filters.remove(it) }
        }
        filters.removeAll { it.isEmpty() }
        return this
    }

    /**
     * Remove all [Filter] from the [filters] list.
     */
    fun clear(): FilterBuilder {
        filters.clear()
        return this
    }

    /**
     * @param group The [Filter.group] used for matching.
     *
     * Retrieve all [Filter] in the [filters] list matching the [group].
     */
    fun getFilters(group: Group): List<Filter> {
        return filters.flatMap {
            it.filter { it.group == group }
        }
    }

    /**
     * @param group The group matching [Filter.group].
     *
     * Remove all [Filter] in [filters] that match the [group].
     */
    fun clear(group: Group): FilterBuilder {
        filters.forEach {
            it.removeAll { it.group == group }
        }
        filters.removeAll { it.isEmpty() }
        return this
    }

    /**
     * @param attribute The attribute matching [Filter.attribute].
     * @param replacement Value used to replace the attribute that matched.
     * @param group A group used for finer grained replacement.
     *
     * Use this method to replace all [Filter] in the [filters] list which have the same [Filter.attribute]
     * as the specified [attribute] with the [replacement]. If you specify a [group],
     * only [Filter] having a matching [Filter.group] will have its [Filter.attribute] replaced by the [replacement].
     *
     * Example:
     *
     * ```
     * val helper = FilterBuilder()
     *
     * val filterA = Filter.Facet("attributeA", "valueA", "groupA")
     * val filterB = Filter.Facet("attributeA", "valueB", "groupB")
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
    fun replaceAttribute(attribute: Attribute, replacement: Attribute, group: Group? = null): FilterBuilder {
        filters.forEach { filters ->
            val list =
                filters.filter {
                    it.attribute == attribute && if (group != null) group == it.group else true
                }

            list.forEach {
                val index = filters.indexOf(it)

                filters.removeAt(index)
                filters.add(index, modifyAttribute(it, replacement))
            }
        }
        return this
    }

    private fun modifyAttribute(filter: Filter, attribute: Attribute): Filter {
        return when (filter) {
            is Filter.Comparison -> filter.copy(attribute = attribute)
            is Filter.Tag -> filter
            is Filter.Boolean -> filter.copy(attribute = attribute)
            is Filter.Facet -> filter.copy(attribute = attribute)
            is Filter.Range -> filter.copy(attribute = attribute)
        }
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