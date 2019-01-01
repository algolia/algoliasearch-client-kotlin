package com.algolia.search.saas

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.Point
import com.algolia.search.saas.data.Snippet


fun String.toIndex(): IndexName {
    return IndexName(this)
}

fun String.toAttribute(): Attribute {
    return Attribute(this)
}

infix fun Float.to(longitude: Float): Point {
    return Point(this, longitude)
}

infix fun Attribute.to(numberOfWords: Int?): Snippet {
    return Snippet(this, numberOfWords)
}