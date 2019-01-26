package com.algolia.search.query

import com.algolia.search.model.Attribute


/**
 * For a better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/]
 */
@QueryHelper
class OptionalFilterBuilder(init: (OptionalFilterBuilder.() -> Unit)? = null) :
    FilterBuilderInterface<FilterFacet> {

    private val groups: GroupMap<FilterFacet> = mutableMapOf()

    init {
        init?.invoke(this)
    }

    override operator fun Group.plusAssign(filter: FilterFacet) {
        groups.add(this, filter)
    }

    override operator fun Group.plusAssign(filters: Collection<FilterFacet>) {
        groups.add(this, *filters.toTypedArray())
    }

    override operator fun Group.minusAssign(filter: FilterFacet) {
        groups.remove(this, filter)
    }

    override operator fun Group.minusAssign(filters: Collection<FilterFacet>) {
        groups.remove(this, *filters.toTypedArray())
    }

    override fun Group.add(filter: FilterFacet) {
        groups.add(this, filter)
    }

    override fun Group.addAll(filters: Collection<FilterFacet>) {
        groups.add(this, *filters.toTypedArray())
    }

    override fun Group.remove(filter: FilterFacet) {
        groups.remove(this, filter)
    }

    override fun Group.removeAll(filters: Collection<FilterFacet>) {
        groups.remove(this, *filters.toTypedArray())
    }

    override fun Group.contains(filter: FilterFacet): Boolean {
        return groups.contains(this, filter)
    }

    override fun Group.clear(attribute: Attribute?) {
        groups.clear(this, attribute)
    }

    override fun Group.replaceAttribute(attribute: Attribute, replacement: Attribute) {
        groups.replaceAttribute(this, attribute, replacement)
    }

    /**
     * Replace in this [Group] a [filter] by its [replacement].
     * @return True if the [filter] was found and successfully replaced.
     */
    fun Group.replace(filter: FilterFacet, replacement: FilterFacet): Boolean {
        return groups.replace(this, filter, replacement)
    }

    override fun Group.move(destination: Group, filter: FilterFacet): Boolean {
        return groups.move(this, destination, filter)
    }

    override fun Group.get(attribute: Attribute?): Set<FilterFacet> {
        return groups.get(this, attribute)
    }

    override fun get(attribute: Attribute?): Set<FilterFacet> {
        return groups.get(attribute)
    }

    override fun contains(filter: FilterFacet): Boolean {
        return groups.contains(filter)
    }

    override fun clear() {
        groups.clear()
    }

    override fun isEmpty(): Boolean {
        return groups.isEmpty()
    }

    /**
     * Express every [Group] and [Filter] present in [groups] into a nested list of [String].
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/]
     */
    fun build(): List<List<String>> {
        val (andEntries, orEntries) = groups.entries.partition { it.key.type == Group.Type.And }
        val ands = andEntries.flatMap { it.value.map { listOf(it.expression) } }
        val ors = orEntries.map { it.value.map { it.expression } }

        return ands + ors
    }
}