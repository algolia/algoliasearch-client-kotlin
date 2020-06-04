package com.algolia.search.transport

import com.algolia.search.model.multicluster.UserID
import com.algolia.search.serialize.KeyAlgoliaUserID
import com.algolia.search.serialize.KeyForwardedFor
import kotlinx.serialization.json.JsonObject

/**
 * Every endpoint can configure a request locally by passing additional
 * [headers], [urlParameters], [body], [writeTimeout], [readTimeout].
 */
public class RequestOptions {

    public val headers = mutableMapOf<String, Any>()
    public val urlParameters = mutableMapOf<String, Any>()

    public var writeTimeout: Long? = null
    public var readTimeout: Long? = null
    public var body: JsonObject? = null

    /**
     * Add a "X-Forwarded-For" header with an [ipAddress] to [headers].
     */
    public fun headerForwardedFor(ipAddress: String) {
        headers[KeyForwardedFor] = ipAddress
    }

    /**
     * Add a "X-Algolia-User-ID" header with an [userId] to [headers].
     */
    public fun headerAlgoliaUserId(userId: UserID) {
        headers[KeyAlgoliaUserID] = userId.raw
    }

    /**
     * Add a url parameter with [key] and [value] to [urlParameters].
     */
    public fun parameter(key: String, value: Any?) {
        value?.let { urlParameters[key] = it }
    }

    /**
     * Add a header with [key] and [value] to [headers].
     */
    public fun header(key: String, value: Any?) {
        value?.let { headers[key] = it }
    }
}
