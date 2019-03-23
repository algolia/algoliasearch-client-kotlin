package com.algolia.search.helper

import com.algolia.search.transport.RequestOptions
import com.algolia.search.model.*
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.Snippet
import com.algolia.search.model.task.TaskID


public fun String.toIndexName(): IndexName {
    return IndexName(this)
}

public fun String.toAttribute(): Attribute {
    return Attribute(this)
}

public fun String.toCursor(): Cursor {
    return Cursor(this)
}

public fun String.toObjectID(): ObjectID {
    return ObjectID(this)
}

public fun Long.toTaskID(): TaskID {
    return TaskID(this)
}

public fun String.toUserID(): UserID {
    return UserID(this)
}

public fun String.toQueryID(): QueryID {
    return QueryID(this)
}

public fun String.toEventName(): EventName {
    return EventName(this)
}

public fun String.toUserToken(): UserToken {
    return UserToken(this)
}

public fun String.toApplicationID(): ApplicationID {
    return ApplicationID(this)
}

public fun String.toAPIKey(): APIKey {
    return APIKey(this)
}

public fun String.toClusterName(): ClusterName {
    return ClusterName(this)
}

public fun Long.toABTestID(): ABTestID {
    return ABTestID(this)
}

public infix fun Float.and(longitude: Float): Point {
    return Point(this, longitude)
}

public infix fun Attribute.limit(numberOfWords: Int?): Snippet {
    return Snippet(this, numberOfWords)
}

public fun requestOptionsBuilder(
    requestOptions: RequestOptions? = null,
    init: RequestOptions.() -> Unit
): RequestOptions {
    return (requestOptions ?: RequestOptions()).apply(init)
}