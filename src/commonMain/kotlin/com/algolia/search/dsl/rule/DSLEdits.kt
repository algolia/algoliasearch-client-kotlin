package com.algolia.search.dsl.rule

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.rule.Edit

/**
 * DSL for building a [List] of [Edit].
 */
@DSLParameters
class DSLEdits(
    private val edits: MutableList<Edit> = mutableListOf()
) {

    /**
     * Add [this] to [edits].
     */
    operator fun Edit.unaryPlus() {
        edits += this
    }

    /**
     * Convenience method.
     */
    operator fun String.unaryPlus() {
        +Edit(this)
    }

    /**
     * Create an [Edit] with [this] and [delete].
     */
    operator fun String.invoke(delete: String): Edit {
        return Edit(this, delete)
    }

    companion object : DSL<DSLEdits, List<Edit>> {

        override operator fun invoke(block: DSLEdits.() -> Unit): List<Edit> {
            return DSLEdits().apply(block).edits
        }
    }
}
