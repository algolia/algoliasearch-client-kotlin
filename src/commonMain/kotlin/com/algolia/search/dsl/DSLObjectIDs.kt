package com.algolia.search.dsl

import com.algolia.search.model.ObjectID


@DSLParameters
public class DSLObjectIDs(
    private val objectIDs: MutableList<ObjectID> = mutableListOf()
) {

    public operator fun String.unaryPlus() {
        +ObjectID(this)
    }

    public operator fun ObjectID.unaryPlus() {
        objectIDs += this
    }

    public companion object : DSL<DSLObjectIDs, List<ObjectID>> {

        override operator fun invoke(block: DSLObjectIDs.() -> Unit): List<ObjectID> {
            return DSLObjectIDs().apply(block).objectIDs
        }
    }
}