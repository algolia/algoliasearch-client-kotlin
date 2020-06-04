package com.algolia.search.dsl.attributes

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.dsl.all
import com.algolia.search.model.Attribute

/**
 * DSL for building a [List] of [Attribute] to retrieve.
 */
@DSLParameters
public class DSLAttributesToRetrieve(
    private val attributes: MutableList<Attribute> = mutableListOf()
) {

    /**
     * True will retrieve all attributes, except the one contained in [attributes].
     * False will retrieve only the attributes contained in [attributes].
     */
    public var excludeAttributes: Boolean = false

    /**
     * Convenience method.
     */
    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    /**
     * Add [this] to [attributes].
     */
    public operator fun Attribute.unaryPlus() {
        attributes += this
    }

    public companion object : DSL<DSLAttributesToRetrieve, List<Attribute>> {

        override operator fun invoke(block: DSLAttributesToRetrieve.() -> Unit): List<Attribute> {
            return DSLAttributesToRetrieve().apply(block).run {
                if (excludeAttributes) {
                    if (attributes.isNotEmpty()) {
                        attributes.map { Attribute("-$it") }.plus(all)
                    } else listOf()
                } else attributes.toList()
            }
        }
    }
}
