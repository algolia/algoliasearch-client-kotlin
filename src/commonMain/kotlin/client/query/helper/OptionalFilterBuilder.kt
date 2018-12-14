package client.query.helper


/**
 * For a better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/]
 */
@QueryHelper
class OptionalFilterBuilder(init: (OptionalFilterBuilder.() -> Unit)? = null) : FilterBuilderInterface<FilterFacet> {

    init {
        init?.invoke(this)
    }

    private val groups: GroupMap<FilterFacet> = mutableMapOf()

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

    override fun Group.get(attribute: Attribute?): Set<FilterFacet> {
        return groups.get(this, attribute)
    }

    override fun clear() {
        groups.clear()
    }

    fun Group.replace(filter: FilterFacet, replacement: FilterFacet) {

    }

    fun build(): List<List<String>> {
        val (andEntries, orEntries) = groups.entries.partition { it.key.key == FilterKey.And }
        val ands = andEntries.flatMap { it.value.map { listOf(it.expression) } }
        val ors = orEntries.map { it.value.map { it.expression } }

        return ands + ors
    }
}