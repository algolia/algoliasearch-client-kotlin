package com.algolia.search.helper

import com.algolia.search.model.Attribute

internal val all = Attribute("*")

internal fun buildAttributesToRetrieve(
    vararg attributes: Attribute,
    excludeAttributes: Boolean = false
): List<Attribute> {
    return if (excludeAttributes) {
        if (attributes.isNotEmpty()) {
            attributes.map { Attribute("-$it") }.plus(all)
        } else listOf()
    } else attributes.toList()
}