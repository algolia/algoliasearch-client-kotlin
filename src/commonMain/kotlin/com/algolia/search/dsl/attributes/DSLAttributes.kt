package com.algolia.search.dsl.attributes

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute


@DSLParameters
public class DSLAttributes(
    private val attributes: MutableList<Attribute> = mutableListOf()
) {

    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    public operator fun Attribute.unaryPlus() {
        attributes += this
    }

    public fun build(): List<Attribute> {
        return attributes.toList()
    }
}