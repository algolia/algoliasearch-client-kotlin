package client.query.helper


/**
 * For a better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/]
 */
@QueryHelper
class OptionalFilterBuilder(init: (OptionalFilterBuilder.() -> Unit)? = null) {

    private val filters: GroupMap = mutableMapOf()

    init {
        init?.invoke(this)
    }

    operator fun Group.minusAssign(filter: FilterFacet) {
        filters.remove(this, filter)
    }

    operator fun Group.minusAssign(filters: Collection<FilterFacet>) {
        this@OptionalFilterBuilder.filters.remove(this, *filters.toTypedArray())
    }

    operator fun Group.plusAssign(filter: FilterFacet) {
        filters.add(this, filter)
    }

    operator fun Group.plusAssign(filters: Collection<FilterFacet>) {
        this@OptionalFilterBuilder.filters.add(this, *filters.toTypedArray())
    }

    fun Group.contains(filter: Filter): Boolean {
        return filters.contains(this, filter)
    }

    fun Group.clear(attribute: Attribute? = null) {
        filters.clear(this, attribute)
    }

    fun Group.replaceAttribute(attribute: Attribute, replacement: Attribute) {
        filters.replaceAttribute(this, attribute, replacement)
    }

    fun Group.get(attribute: Attribute? = null): Set<FilterFacet> {
        return filters.get(this, attribute).filterIsInstance<FilterFacet>().toSet()
    }

    fun clear() {
        filters.clear()
    }

    fun build(): List<List<String>> {
        val (andEntries, orEntries) = filters.entries.partition { it.key.key == ClassKey.Filter }
        val ands = andEntries.flatMap { it.value.map { listOf(it.expression) } }
        val ors = orEntries.map { it.value.map { it.expression } }

        return ands + ors
    }
}