package com.algolia.search.dsl.highlighting

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Snippet

/**
 * DSL for building a [List] of [Snippet].
 */
@DSLParameters
class DSLSnippet(
    private val snippets: MutableList<Snippet> = mutableListOf()
) {

    /**
     * Convenience method.
     */
    operator fun String.unaryPlus() {
        +Attribute(this)
    }

    /**
     * Convenience method.
     */
    operator fun Attribute.unaryPlus() {
        +Snippet(this)
    }

    /**
     * Add [this] to [snippets].
     */
    operator fun Snippet.unaryPlus() {
        snippets += this
    }

    /**
     * Convenience method.
     */
    operator fun String.invoke(count: Int): Snippet {
        return Attribute(this).invoke(count)
    }

    /**
     * Create a [Snippet] with [this] [Attribute] and [count].
     */
    operator fun Attribute.invoke(count: Int): Snippet {
        return Snippet(this, count)
    }

    companion object : DSL<DSLSnippet, List<Snippet>> {

        override operator fun invoke(block: DSLSnippet.() -> Unit): List<Snippet> {
            return DSLSnippet().apply(block).snippets
        }
    }
}
