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

    operator fun Group.Or.plusAssign(filter: Filter) {
        ors.add(this, filter)
    }

    operator fun Group.Or.plusAssign(filters: Collection<Filter>) {
        ors.add(this, *filters.toTypedArray())
    }

    operator fun Group.Or.minusAssign(filter: Filter) {
        when (filter) {
            is FilterNumeric -> ors.remove(this, filter)
            is FilterTag -> ors.remove(this, filter)
            is FilterFacet -> ors.remove(this, filter)
        }
    }

    operator fun Group.And.plusAssign(filter: Filter) {
        ands.add(this, filter)
    }

    operator fun Group.And.plusAssign(filters: Collection<Filter>) {
        ands.add(this, *filters.toTypedArray())
    }

    fun Group.And.remove(vararg filters: Filter) {
        ands.remove(this, *filters)
    }

    operator fun Group.And.minusAssign(filters: Filter) {
        ands.remove(this, filters)
    }

    operator fun Group.And.minusAssign(filters: Collection<Filter>) {
        ands.remove(this, *filters.toTypedArray())
    }

    /// ***********
    /// GROUP
    /// ***********

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

    fun clear() {
        ands.clear()
        ors.clear()
    }

    fun build(): String {
        val ands = ands.let {
            val condition = ands.size > 1
            val prefix = if (condition) "(" else ""
            val postfix = if (condition) ")" else ""

            ands.values.joinToString(prefix = prefix, postfix = postfix, separator = " AND ") {
                it.joinToString(separator = " AND ") { it.build() }
            }
        }
        val ors = ors.let {
            val begin = if (this.ands.isNotEmpty()) " AND " else ""
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