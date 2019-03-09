package com.algolia.search.helper

import com.algolia.search.client.ClientAnalytics
import com.algolia.search.client.Index
import com.algolia.search.client.RequestOptions
import com.algolia.search.model.*
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.response.ResponseABTests
import com.algolia.search.model.response.ResponseRules
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.search.*
import com.algolia.search.model.synonym.SynonymType
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

public fun requestOptions(init: RequestOptions.() -> Unit): RequestOptions {
    return RequestOptions().apply(init)
}

public suspend fun Index.browseAllRules(
    query: String? = null,
    anchoring: Anchoring? = null,
    context: String? = null,
    hitsPerPage: Int? = null,
    enabled: Boolean? = null,
    requestOptions: RequestOptions? = null,
    block: suspend ResponseRules.(Int) -> Unit
) {
    var page = 0

    while (true) {
        val response = searchRules(query, anchoring, context, page, hitsPerPage, enabled, requestOptions)

        if (response.hits.isEmpty()) break
        block(response, page++)
    }
}

public suspend fun Index.browseAllSynonyms(
    query: String? = null,
    hitsPerPage: Int? = null,
    synonymType: List<SynonymType>? = null,
    requestOptions: RequestOptions? = null,
    block: suspend ResponseSearchSynonyms.(Int) -> Unit
) {
    var page = 0

    while (true) {
        val response = searchSynonyms(query, page, hitsPerPage, synonymType, requestOptions)

        if (response.hits.isEmpty()) break
        block(response, page++)
    }
}

public suspend fun Index.browseAllObjects(
    query: Query? = null,
    requestOptions: RequestOptions? = null,
    block: suspend ResponseSearch.(Int) -> Unit
) {
    val initial = browse(query, requestOptions)
    var cursor = initial.cursorOrNull
    var page = 0

    block(initial, page++)
    while (cursor != null) {
        val response = browse(cursor)

        block(response, page++)
        cursor = response.cursorOrNull
    }
}

public suspend fun ClientAnalytics.browseAllABTests(
    hitsPerPage: Int? = null,
    requestOptions: RequestOptions? = null,
    block: suspend ResponseABTests.(Int) -> Unit
) {
    var page = 0

    while (true) {
        val response = listABTests(page, hitsPerPage, requestOptions)

        if (response.count == 0) break
        block(response, page++)
    }
}

public operator fun List<Facet>.get(name: String): Int {
    return find { it.name == name }!!.count
}

public operator fun Map<Attribute, List<Facet>>.get(attribute: Attribute, name: String): Int {
    return getValue(attribute).find { it.name == name }!!.count
}