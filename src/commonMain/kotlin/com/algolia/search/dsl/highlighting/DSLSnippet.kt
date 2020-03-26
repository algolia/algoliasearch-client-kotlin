package com.algolia.search.dsl.highlighting

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Snippet

/**
 * DSL for building a [List] of [Snippet].
 */
@DSLParameters
public class DSLSnippet(
    private val snippets: MutableList<Snippet> = mutableListOf()
) {

    /**
     * Convenience method.
     */
    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    /**
     * Convenience method.
     */
    public operator fun Attribute.unaryPlus() {
        +Snippet(this)
    }

    /**
     * Add [this] to [snippets].
     */
    public operator fun Snippet.unaryPlus() {
        snippets += this
    }

    /**
     * Convenience method.
     */
    public operator fun String.invoke(count: Int): Snippet {
        return Attribute(this).invoke(count)
    }

    /**
     * Create a [Snippet] with [this] [Attribute] and [count].
     */
    public operator fun Attribute.invoke(count: Int): Snippet {
        return Snippet(this, count)
    }

    public companion object : DSL<DSLSnippet, List<Snippet>> {

        override operator fun invoke(block: DSLSnippet.() -> Unit): List<Snippet> {
            return DSLSnippet().apply(block).snippets
        }
    }
}
