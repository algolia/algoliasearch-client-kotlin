package com.algolia.search.dsl

import com.algolia.search.model.IndexName


@DSLParameters
public class DSLReplicas {

    private val replicas: MutableList<IndexName> = mutableListOf()

    public operator fun String.unaryPlus() {
        +IndexName(this)
    }

    public operator fun IndexName.unaryPlus() {
        replicas += this
    }

    public fun build(): List<IndexName> {
        return replicas.toList()
    }
}