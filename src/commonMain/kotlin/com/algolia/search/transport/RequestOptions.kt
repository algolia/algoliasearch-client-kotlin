package com.algolia.search.transport

import com.algolia.search.model.multicluster.UserID
import com.algolia.search.serialize.KeyAlgoliaUserID
import com.algolia.search.serialize.KeyForwardedFor
import kotlinx.serialization.json.JsonObject

/**
 * Every endpoint can configure a request locally by passing additional
 * [headers], [urlParameters], [body], [writeTimeout], [readTimeout].
 */
class RequestOptions {

    val headers = mutableMapOf<String, Any>()
    val urlParameters = mutableMapOf<String, Any>()

    var writeTimeout: Long? = null
    var readTimeout: Long? = null
    var body: JsonObject? = null

    /**
     * Add a "X-Forwarded-For" header with an [ipAddress] to [headers].
     */
    fun headerForwardedFor(ipAddress: String) {
        headers[KeyForwardedFor] = ipAddress
    }

    /**
     * Add a "X-Algolia-User-ID" header with an [userId] to [headers].
     */
    fun headerAlgoliaUserId(userId: UserID) {
        headers[KeyAlgoliaUserID] = userId.raw
    }

    /**
     * Add a url parameter with [key] and [value] to [urlParameters].
     */
    fun parameter(key: String, value: Any?) {
        value?.let { urlParameters[key] = it }
    }

    /**
     * Add a header with [key] and [value] to [headers].
     */
    fun header(key: String, value: Any?) {
        value?.let { headers[key] = it }
    }
}
