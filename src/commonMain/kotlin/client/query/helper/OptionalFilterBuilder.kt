package client.query.helper


/**
 * For a better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/]
 */
@QueryHelper
class OptionalFilterBuilder(init: (OptionalFilterBuilder.() -> Unit)? = null) : AbstractFilterBuilder<FilterFacet>() {

    init {
        init?.invoke(this)
    }

    override fun Group.replaceAttribute(attribute: Attribute, replacement: Attribute) {
        groups.replaceAttribute(this, attribute, replacement)
    }

    fun build(): List<List<String>> {
        val (andEntries, orEntries) = groups.entries.partition { it.key.key == FilterKey.And }
        val ands = andEntries.flatMap { it.value.map { listOf(it.expression) } }
        val ors = orEntries.map { it.value.map { it.expression } }

        return ands + ors
    }
}