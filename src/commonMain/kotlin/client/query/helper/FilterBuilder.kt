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

    fun Group.Or.add(vararg filters: FilterFacet) {
        ors.add(this, filters)
    }

    fun Group.Or.add(vararg filters: FilterTag) {
        ors.add(this, filters)
    }

    fun Group.Or.add(vararg filters: FilterNumeric) {
        ors.add(this, filters)
    }

    fun Group.Or.remove(vararg filters: FilterFacet) {
        ors.remove(this, filters)
    }

    fun Group.Or.remove(vararg filters: FilterNumeric) {
        ors.remove(this, filters)
    }

    fun Group.Or.remove(vararg filters: FilterTag) {
        ors.remove(this, filters)
    }

    fun Group.And.add(vararg filters: Filter) {
        ands.add(this, filters)
    }

    fun Group.And.remove(vararg filters: Filter) {
        ands.remove(this, filters)
    }

    fun Group.contains(filter: Filter): Boolean {
        return when (this) {
            is Group.And -> ands.containsReified(this, filter)
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