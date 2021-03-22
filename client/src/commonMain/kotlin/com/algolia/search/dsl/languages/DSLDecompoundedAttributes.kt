package com.algolia.search.dsl.languages

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.dsl.attributes.DSLAttributes
import com.algolia.search.model.search.Language
import com.algolia.search.model.settings.DecompoundedAttributes

/**
 * DSL for building a [List] of [DecompoundedAttributes].
 */
@DSLParameters
public class DSLDecompoundedAttributes(
    private val decompoundedAttributes: MutableList<DecompoundedAttributes> = mutableListOf()
) {

    private infix fun Language.decompounded(block: DSLAttributes.() -> Unit): DecompoundedAttributes {
        return DecompoundedAttributes(this, DSLAttributes(block))
    }

    /**
     * Create and add a [DecompoundedAttributes] for the [Language.German] using [block] to [decompoundedAttributes].
     */
    public infix fun german(block: DSLAttributes.() -> Unit) {
        +Language.German.decompounded(block)
    }

    /**
     * Create and add a [DecompoundedAttributes] for the [Language.Finnish] using [block] to [decompoundedAttributes].
     */
    public infix fun finnish(block: DSLAttributes.() -> Unit) {
        +Language.Finnish.decompounded(block)
    }

    /**
     * Create and add a [DecompoundedAttributes] for the [Language.Dutch] using [block] to [decompoundedAttributes].
     */
    public infix fun dutch(block: DSLAttributes.() -> Unit) {
        +Language.Dutch.decompounded(block)
    }

    /**
     * Add [this] to [decompoundedAttributes].
     */
    public operator fun DecompoundedAttributes.unaryPlus() {
        decompoundedAttributes += this
    }

    public companion object : DSL<DSLDecompoundedAttributes, List<DecompoundedAttributes>> {

        override operator fun invoke(block: DSLDecompoundedAttributes.() -> Unit): List<DecompoundedAttributes> {
            return DSLDecompoundedAttributes().apply(block).decompoundedAttributes
        }
    }
}
