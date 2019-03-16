package com.algolia.search.transport

import com.algolia.search.model.UserID
import com.algolia.search.serialize.KeyAlgoliaUserID
import com.algolia.search.serialize.KeyForwardedFor
import kotlinx.serialization.json.JsonObject


public data class RequestOptions(
    val writeTimeout: Long? = null,
    val readTimeout: Long? = null
) {

    internal val headers = mutableMapOf<String, Any>()

    internal val urlParameters = mutableMapOf<String, Any>()

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