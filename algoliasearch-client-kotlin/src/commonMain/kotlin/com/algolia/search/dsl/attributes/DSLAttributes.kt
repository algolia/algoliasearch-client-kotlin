package com.algolia.search.dsl.attributes

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute

/**
 * DSL for building a [List] of [Attribute].
 */
@DSLParameters
public class DSLAttributes(
    private val attributes: MutableList<Attribute> = mutableListOf()
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

    public companion object : DSL<DSLAttributes, List<Attribute>> {

        override operator fun invoke(block: DSLAttributes.() -> Unit): List<Attribute> {
            return DSLAttributes().apply(block).attributes
        }
    }
}
