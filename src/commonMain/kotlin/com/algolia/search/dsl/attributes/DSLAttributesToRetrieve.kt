package com.algolia.search.dsl.attributes

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.dsl.all
import com.algolia.search.model.Attribute


@DSLParameters
public class DSLAttributesToRetrieve(
    private val attributes: MutableList<Attribute> = mutableListOf()
) {

    public var excludeAttributes: Boolean = false

    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    public operator fun Attribute.unaryPlus() {
        attributes += this
    }

    public fun build(): List<Attribute> {
        return if (excludeAttributes) {
            if (attributes.isNotEmpty()) {
                attributes.map { Attribute("-$it") }.plus(all)
            } else listOf()
        } else attributes.toList()
    }
}