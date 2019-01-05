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

fun String.toObjectId(): ObjectId {
    return ObjectId(this)
}

fun Long.toTaskId(): TaskId {
    return TaskId(this)
}


infix fun Float.to(longitude: Float): Point {
    return Point(this, longitude)
}

infix fun Attribute.to(numberOfWords: Int?): Snippet {
    return Snippet(this, numberOfWords)
}