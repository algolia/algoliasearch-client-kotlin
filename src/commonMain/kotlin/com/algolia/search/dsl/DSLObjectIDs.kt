package com.algolia.search.dsl

import com.algolia.search.model.ObjectID

/**
 * DSL for building a [List] of [ObjectID].
 */
@DSLParameters
public class DSLObjectIDs(
    private val objectIDs: MutableList<ObjectID> = mutableListOf()
) {

    /**
     * Convenience method.
     */
    public operator fun String.unaryPlus() {
        +ObjectID(this)
    }

    /**
     * Add [this] to [objectIDs].
     */
    public operator fun ObjectID.unaryPlus() {
        objectIDs += this
    }

    public companion object : DSL<DSLObjectIDs, List<ObjectID>> {

        override operator fun invoke(block: DSLObjectIDs.() -> Unit): List<ObjectID> {
            return DSLObjectIDs().apply(block).objectIDs
        }
    }
}
