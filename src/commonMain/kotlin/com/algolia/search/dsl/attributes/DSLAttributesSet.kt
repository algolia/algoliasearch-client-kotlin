package com.algolia.search.dsl.attributes

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute

/**
 * DSL for building a [Set] of [Attribute].
 */
@DSLParameters
public class DSLAttributesSet(
    private val attributes: MutableSet<Attribute> = mutableSetOf()
) {

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

    public companion object : DSL<DSLAttributesSet, Set<Attribute>> {

        override operator fun invoke(block: DSLAttributesSet.() -> Unit): Set<Attribute> {
            return DSLAttributesSet().apply(block).attributes
        }
    }
}
