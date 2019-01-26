package com.algolia.search.saas

import com.algolia.search.saas.model.*
import com.algolia.search.saas.model.api_key.APIKey
import com.algolia.search.saas.model.cluster.ClusterName
import com.algolia.search.saas.model.common.TaskID
import com.algolia.search.saas.model.enums.Point
import com.algolia.search.saas.model.enums.Snippet
import com.algolia.search.saas.model.search.Cursor


fun String.toIndexName(): IndexName {
    return IndexName(this)
}

fun String.toAttribute(): Attribute {
    return Attribute(this)
}

fun String.toCursor(): Cursor {
    return Cursor(this)
}

fun String.toObjectID(): ObjectID {
    return ObjectID(this)
}

fun Long.toTaskID(): TaskID {
    return TaskID(this)
}

fun String.toUserID(): UserID {
    return UserID(this)
}

fun String.toApplicationID(): ApplicationID {
    return ApplicationID(this)
}

fun String.toAPIKey(): APIKey {
    return APIKey(this)
}

fun String.toClusterName(): ClusterName {
    return ClusterName(this)
}


infix fun Float.to(longitude: Float): Point {
    return Point(this, longitude)
}

infix fun Attribute.to(numberOfWords: Int?): Snippet {
    return Snippet(this, numberOfWords)
}