package client

import client.data.Attribute
import client.data.Index
import client.data.Point
import client.data.Snippet


fun String.toIndex(): Index {
    return Index(this)
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