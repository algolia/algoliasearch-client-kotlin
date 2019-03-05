package com.algolia.search

import com.algolia.search.client.ClientAnalytics
import com.algolia.search.client.Index
import com.algolia.search.client.RequestOptions
import com.algolia.search.model.*
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.response.ResponseABTests
import com.algolia.search.model.response.ResponseRules
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.Snippet
import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.model.task.TaskID


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

fun Long.toABTestID(): ABTestID {
    return ABTestID(this)
}

infix fun Float.and(longitude: Float): Point {
    return Point(this, longitude)
}

infix fun Attribute.limit(numberOfWords: Int?): Snippet {
    return Snippet(this, numberOfWords)
}

fun requestOptions(init: RequestOptions.() -> Unit): RequestOptions {
    return RequestOptions().apply(init)
}

suspend fun Index.browseAllRules(
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

suspend fun Index.browseAllSynonyms(
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

suspend fun Index.browseAllObjects(
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

suspend fun ClientAnalytics.browseAllABTests(
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