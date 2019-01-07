package com.algolia.search.saas

import com.algolia.search.saas.data.*


fun String.toIndexName(): IndexName {
    return IndexName(this)
}

fun String.toAttribute(): Attribute {
    return Attribute(this)
}

fun String.toCursor(): Cursor {
    return Cursor(this)
}

fun String.toObjectId(): ObjectID {
    return ObjectID(this)
}

fun Long.toTaskId(): TaskID {
    return TaskID(this)
}


infix fun Float.to(longitude: Float): Point {
    return Point(this, longitude)
}

infix fun Attribute.to(numberOfWords: Int?): Snippet {
    return Snippet(this, numberOfWords)
}