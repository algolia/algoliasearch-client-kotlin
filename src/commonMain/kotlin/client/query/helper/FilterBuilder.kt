package client.query.helper


/**
 * For a better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
 */
@QueryHelper
class FilterBuilder(init: (FilterBuilder.() -> Unit)? = null) : FilterBuilderInterface<Filter> {

    init {
        init?.invoke(this)
    }

    private val groups: GroupMap<Filter> = mutableMapOf()

    override operator fun Group.plusAssign(filter: Filter) {
        groups.add(this, filter)
    }

    override operator fun Group.plusAssign(filters: Collection<Filter>) {
        filters.forEach { groups.add(this, it) }
    }

    override operator fun Group.minusAssign(filter: Filter) {
        groups.remove(this, filter)
    }

    override operator fun Group.minusAssign(filters: Collection<Filter>) {
        filters.forEach { groups.remove(this, it) }
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

    override fun Group.get(attribute: Attribute?): Set<Filter> {
        return groups.get(this, attribute)
    }

    override fun clear() {
        groups.clear()
    }

    fun build(): String {
        val (andEntries, orEntries) = groups.entries.partition { it.key.key == FilterKey.And }
        val ands = andEntries.joinToString(separator = " AND ") {
            val condition = andEntries.size > 1 && it.value.size > 1
            val prefix = if (condition) "(" else ""
            val postfix = if (condition) ")" else ""
            it.value.joinToString(prefix = prefix, postfix = postfix, separator = " AND ") { it.build() }
        }
        val begin = if (andEntries.isNotEmpty() && orEntries.isNotEmpty()) " AND " else ""
        val ors = orEntries.joinToString(prefix = begin, separator = " AND ") {
            val condition = it.value.size > 1 && (orEntries.size > 1 || andEntries.isNotEmpty())
            val prefix = if (condition) "(" else ""
            val postfix = if (condition) ")" else ""
            it.value.joinToString(prefix = prefix, postfix = postfix, separator = " OR ") { it.build() }
        }
        return ands + ors
    }
}