package com.algolia.search.dsl.ranking

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.IndexName

/**
 * DSL for building a [List] of [IndexName].
 */
@DSLParameters
class DSLIndexName(
    private val indexNames: MutableList<IndexName> = mutableListOf()
) {

    /**
     * Convenience method.
     */
    operator fun String.unaryPlus() {
        +IndexName(this)
    }

    /**
     * Add [this] to [indexNames].
     */
    operator fun IndexName.unaryPlus() {
        indexNames += this
    }

    companion object : DSL<DSLIndexName, List<IndexName>> {

        override operator fun invoke(block: DSLIndexName.() -> Unit): List<IndexName> {
            return DSLIndexName().apply(block).indexNames
        }
    }
}
