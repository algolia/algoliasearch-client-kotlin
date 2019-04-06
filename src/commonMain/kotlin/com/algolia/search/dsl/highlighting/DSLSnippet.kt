package com.algolia.search.dsl.highlighting

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Snippet


@DSLParameters
public class DSLSnippet(
    private val snippets: MutableList<Snippet> = mutableListOf()
) {

    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    public operator fun Attribute.unaryPlus() {
        +Snippet(this)
    }

    public operator fun Snippet.unaryPlus() {
        snippets += this
    }

    public operator fun String.invoke(count: Int): Snippet {
        return Attribute(this).invoke(count)
    }

    public operator fun Attribute.invoke(count: Int): Snippet {
        return Snippet(this, count)
    }

    public companion object : DSL<DSLSnippet, List<Snippet>> {

        override operator fun invoke(block: DSLSnippet.() -> Unit): List<Snippet> {
            return DSLSnippet().apply(block).snippets
        }
    }
}