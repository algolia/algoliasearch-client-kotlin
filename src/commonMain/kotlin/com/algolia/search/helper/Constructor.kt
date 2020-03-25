package com.algolia.search.helper

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.QueryID
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Point
import com.algolia.search.model.task.TaskID

/**
 * Convenience method to convert [this] to an [IndexName].
 */
fun String.toIndexName(): IndexName {
    return IndexName(this)
}

/**
 * Convenience method to convert [this] to an [Attribute].
 */
fun String.toAttribute(): Attribute {
    return Attribute(this)
}

/**
 * Convenience method to convert [this] to a [Cursor].
 */
fun String.toCursor(): Cursor {
    return Cursor(this)
}

/**
 * Convenience method to convert [this] to an [ObjectID].
 */
fun String.toObjectID(): ObjectID {
    return ObjectID(this)
}

/**
 * Convenience method to convert [this] to a [TaskID].
 */
fun Long.toTaskID(): TaskID {
    return TaskID(this)
}

/**
 * Convenience method to convert [this] to an [UserID].
 */
fun String.toUserID(): UserID {
    return UserID(this)
}

/**
 * Convenience method to convert [this] to a [QueryID].
 */
fun String.toQueryID(): QueryID {
    return QueryID(this)
}

/**
 * Convenience method to convert [this] to an [EventName].
 */
fun String.toEventName(): EventName {
    return EventName(this)
}

/**
 * Convenience method to convert [this] to an [UserToken].
 */
fun String.toUserToken(): UserToken {
    return UserToken(this)
}

/**
 * Convenience method to convert [this] to an [ApplicationID].
 */
fun String.toApplicationID(): ApplicationID {
    return ApplicationID(this)
}

/**
 * Convenience method to convert [this] to an [APIKey].
 */
fun String.toAPIKey(): APIKey {
    return APIKey(this)
}

/**
 * Convenience method to convert [this] to a [ClusterName].
 */
fun String.toClusterName(): ClusterName {
    return ClusterName(this)
}

/**
 * Convenience method to convert [this] to an [ABTestID].
 */
fun Long.toABTestID(): ABTestID {
    return ABTestID(this)
}

/**
 * Convenience method to create a [Point] with [this] as [Point.latitude] and [longitude] as [Point.longitude].
 */
infix fun Float.and(longitude: Float): Point {
    return Point(this, longitude)
}
