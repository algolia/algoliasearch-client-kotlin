package client.query.helper


/**
 * For a better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
 */
@QueryHelper
class FilterBuilder(init: (FilterBuilder.() -> Unit)? = null) {

    private val ors: GroupMap = mutableMapOf()
    private val ands: GroupMap = mutableMapOf()

    init {
        init?.invoke(this)
    }

    operator fun Group.minusAssign(filter: Filter) {
        when (this) {
            is Group.And -> ands.remove(this, filter)
            is Group.Or -> ors.remove(this, filter)
        }
    }

    operator fun Group.minusAssign(filter: Collection<Filter>) {
        when (this) {
            is Group.And -> ands.remove(this, *filter.toTypedArray())
            is Group.Or -> ors.remove(this, *filter.toTypedArray())
        }
    }

    operator fun Group.plusAssign(filter: Filter) {
        when (this) {
            is Group.And -> ands.add(this, filter)
            is Group.Or -> ors.add(this, filter)
        }
    }

    operator fun Group.plusAssign(filters: Collection<Filter>) {
        when (this) {
            is Group.And -> ands.add(this, *filters.toTypedArray())
            is Group.Or -> ors.add(this, *filters.toTypedArray())
        }
    }

    fun Group.contains(filter: Filter): Boolean {
        return when (this) {
            is Group.And -> ands.contains(this, filter)
            is Group.Or -> ors.contains(this, filter)
        }
    }

    fun Group.clear(attribute: Attribute? = null) {
        when (this) {
            is Group.And -> ands.clear(this, attribute)
            is Group.Or -> ors.clear(this, attribute)
        }
    }

    fun Group.replaceAttribute(attribute: Attribute, replacement: Attribute) {
        when (this) {
            is Group.And -> ands.replaceAttribute(this, attribute, replacement)
            is Group.Or -> ors.replaceAttribute(this, attribute, replacement)
        }
    }

    fun Group.get(attribute: Attribute? = null): Set<Filter> {
        return when (this) {
            is Group.And -> ands.get(this, attribute)
            is Group.Or -> ors.get(this, attribute)
        }
    }

    fun clear() {
        ands.clear()
        ors.clear()
    }

    fun build(): String {
        val ands = ands.let {
            ands.values.joinToString(separator = " AND ") {
                val condition = ands.size > 1 && it.size > 1
                val prefix = if (condition) "(" else ""
                val postfix = if (condition) ")" else ""
                it.joinToString(prefix = prefix, postfix = postfix, separator = " AND ") { it.build() }
            }
        }
        val ors = ors.let {
            val begin = if (this.ands.isNotEmpty() && it.isNotEmpty()) " AND " else ""
            ors.values.joinToString(prefix = begin, separator = " AND ") {
                val condition = it.size > 1 && (ors.size > 1 || this.ands.isNotEmpty())
                val prefix = if (condition) "(" else ""
                val postfix = if (condition) ")" else ""
                it.joinToString(prefix = prefix, postfix = postfix, separator = " OR ") { it.build() }
            }
        }
        return ands + ors
    }
}