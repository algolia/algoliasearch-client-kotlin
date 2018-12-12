package client.query.helper


/**
 * For a better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
 */
@QueryHelper
class FilterBuilder(init: (FilterBuilder.() -> Unit)? = null) {

    private val filters: GroupMap = mutableMapOf()

    init {
        init?.invoke(this)
    }

    operator fun Group.minusAssign(filter: Filter) {
        filters.remove(this, filter)
    }

    operator fun Group.minusAssign(filters: Collection<Filter>) {
        this@FilterBuilder.filters.remove(this, *filters.toTypedArray())
    }

    operator fun Group.plusAssign(filter: Filter) {
        filters.add(this, filter)
    }

    operator fun Group.plusAssign(filters: Collection<Filter>) {
        this@FilterBuilder.filters.add(this, *filters.toTypedArray())
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

    fun Group.get(attribute: Attribute? = null): Set<Filter> {
        return filters.get(this, attribute)
    }

    fun clear() {
        filters.clear()
    }

    fun build(): String {
        val (andEntries, orEntries) = filters.entries.partition { it.key.key == ClassKey.Filter }
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