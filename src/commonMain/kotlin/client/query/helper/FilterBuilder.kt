package client.query.helper


/**
 * For a better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
 */
@QueryHelper
class FilterBuilder(init: (FilterBuilder.() -> Unit)? = null) : AbstractFilterBuilder<Filter>() {

    init {
        init?.invoke(this)
    }

    override fun Group.replaceAttribute(attribute: Attribute, replacement: Attribute) {
        groups.replaceAttribute(this, attribute, replacement)
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