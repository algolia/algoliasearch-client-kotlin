package com.algolia.search.dsl

import com.algolia.search.model.ObjectID

/**
 * DSL for building a [List] of [ObjectID].
 */
@DSLParameters
class DSLObjectIDs(
    private val objectIDs: MutableList<ObjectID> = mutableListOf()
) {

    /**
     * Convenience method.
     */
    operator fun String.unaryPlus() {
        +ObjectID(this)
    }

    /**
     * Add [this] to [objectIDs].
     */
    operator fun ObjectID.unaryPlus() {
        objectIDs += this
    }

    companion object : DSL<DSLObjectIDs, List<ObjectID>> {

        override operator fun invoke(block: DSLObjectIDs.() -> Unit): List<ObjectID> {
            return DSLObjectIDs().apply(block).objectIDs
        }
    }
}
