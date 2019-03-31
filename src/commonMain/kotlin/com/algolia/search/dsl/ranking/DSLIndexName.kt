package com.algolia.search.dsl.ranking

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.IndexName


@DSLParameters
public class DSLIndexName(
    private val indexNames: MutableList<IndexName> = mutableListOf()
) {

    public operator fun String.unaryPlus() {
        +IndexName(this)
    }

    public operator fun IndexName.unaryPlus() {
        indexNames += this
    }

    public fun build(): List<IndexName> {
        return indexNames.toList()
    }
}