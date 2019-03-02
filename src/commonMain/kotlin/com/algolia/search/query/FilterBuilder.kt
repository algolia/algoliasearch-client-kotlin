package com.algolia.search.query

import com.algolia.search.model.Attribute


/**
 * @see FilterBuilderInterface
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
 */
@QueryHelper
public class FilterBuilder(init: (FilterBuilder.() -> Unit)? = null) : FilterBuilderInterface<Filter> {

    private val groups: GroupMap<Filter> = mutableMapOf()

    init {
        init?.invoke(this)
    }

    override operator fun Group.plusAssign(filter: Filter) {
        groups.add(this, filter)
    }

    override operator fun Group.plusAssign(filters: Collection<Filter>) {
        addAll(filters)
    }

    override operator fun Group.minusAssign(filter: Filter) {
        groups.remove(this, filter)
    }

    override operator fun Group.minusAssign(filters: Collection<Filter>) {
        removeAll(filters)
    }

    override fun Group.add(filter: Filter) {
        groups.add(this, filter)
    }

    override fun Group.addAll(filters: Collection<Filter>) {
        groups.add(this, *filters.toTypedArray())
    }

    override fun Group.remove(filter: Filter) {
        groups.remove(this, filter)
    }

    override fun Group.removeAll(filters: Collection<Filter>) {
        groups.remove(this, *filters.toTypedArray())
    }

    override fun Group.addOrRemove(filter: Filter): Boolean {
        return groups.addOrRemove(this, filter)
    }

    override fun Group.contains(filter: Filter): Boolean {
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
    public fun Group.replace(filter: FilterFacet, replacement: FilterFacet): Boolean {
        return groups.replace(this, filter, replacement)
    }

    /**
     * Replace in this [Group] a [filter] by its [replacement].
     * @return True if the [filter] was found and successfully replaced.
     */
    public fun Group.replace(filter: FilterNumeric, replacement: FilterNumeric): Boolean {
        return groups.replace(this, filter, replacement)
    }

    /**
     * Replace in this [Group] a [filter] by its [replacement].
     * @return True if the [filter] was found and successfully replaced.
     */
    public fun Group.replace(filter: FilterTag, replacement: FilterTag): Boolean {
        return groups.replace(this, filter, replacement)
    }

    override fun Group.move(destination: Group, filter: Filter): Boolean {
        return groups.move(this, destination, filter)
    }

    override fun Group.get(attribute: Attribute?): Set<Filter> {
        return groups.get(this, attribute)
    }

    override fun get(attribute: Attribute?): Set<Filter> {
        return groups.get(attribute)
    }

    override fun contains(filter: Filter): Boolean {
        return groups.contains(filter)
    }

    override fun clear() {
        groups.clear()
    }

    override fun isEmpty(): Boolean {
        return groups.isEmpty()
    }

    /**
     * Express every [Group] and [Filter] present in [groups] into a [String].
     * This [String] uses SQL-like syntax, where boolean operators and parenthesis can be used to combined individual
     * filters.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
     */
    public fun build(): String {
        val (andEntries, orEntries) = groups.entries.partition { it.key.type == Group.Type.And }
        val ands = andEntries.joinToString(separator = " AND ") { (_, value) ->
            val condition = andEntries.size > 1 && value.size > 1
            val prefix = if (condition) "(" else ""
            val postfix = if (condition) ")" else ""
            value.joinToString(prefix = prefix, postfix = postfix, separator = " AND ") { it.build() }
        }
        val begin = if (andEntries.isNotEmpty() && orEntries.isNotEmpty()) " AND " else ""
        val ors = orEntries.joinToString(prefix = begin, separator = " AND ") { (_, value) ->
            val condition = value.size > 1 && (orEntries.size > 1 || andEntries.isNotEmpty())
            val prefix = if (condition) "(" else ""
            val postfix = if (condition) ")" else ""
            value.joinToString(prefix = prefix, postfix = postfix, separator = " OR ") { it.build() }
        }
        return ands + ors
    }

    internal fun printDebug() {
        println(buildString {
            groups.keys.sortedBy { it.name }.forEach {
                append("$it : ${groups[it]}\n")
            }
        })
    }
}