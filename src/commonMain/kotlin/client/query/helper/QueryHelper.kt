package client.query.helper

import client.query.Query


fun queryBuilder(init: Query.() -> Unit) = Query().apply(init)

fun Query.attributesToRetrieve(vararg attribute: String, excludeAttributes: Boolean = false) {
    attributesToRetrieve = if (excludeAttributes) {
        if (attribute.isNotEmpty()) {
            attribute.map { "-$it" }.plus("*")
        } else listOf()
    } else attribute.toList()
}

fun Query.restrictSearchableAttribute(vararg attribute: String) {
    restrictSearchableAttributes = attribute.toList()
}

fun example() {
    val query = queryBuilder {
        attributesToRetrieve("attributeA", "attributeB", excludeAttributes = true)
    }
    println(query.attributesToRetrieve) // ["-attributeA", "-attributeB", "*"]
}

