package com.algolia.search.transport

import com.algolia.search.model.multicluster.UserID
import com.algolia.search.serialize.KeyAlgoliaUserID
import com.algolia.search.serialize.KeyForwardedFor
import kotlinx.serialization.json.JsonObject


public class RequestOptions {

    public val headers = mutableMapOf<String, Any>()
    public val urlParameters = mutableMapOf<String, Any>()

    public var writeTimeout: Long? = null
    public var readTimeout: Long? = null
    public var body: JsonObject? = null

    public fun headerForwardedFor(ipAddress: String) {
        headers[KeyForwardedFor] = ipAddress
    }

    public fun headerAlgoliaUserId(userId: UserID) {
        headers[KeyAlgoliaUserID] = userId.raw
    }

    public fun parameter(key: String, value: Any?) {
        value?.let { urlParameters[key] = it }
    }

    public fun header(key: String, value: Any?) {
        value?.let { headers[key] = it }
    }
}