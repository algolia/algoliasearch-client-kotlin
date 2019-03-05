package com.algolia.search.client

import com.algolia.search.model.UserID
import com.algolia.search.serialize.KeyAlgoliaUserID
import com.algolia.search.serialize.KeyForwardedFor
import kotlinx.serialization.json.JsonObject


public data class RequestOptions(
    val writeTimeout: Long? = null,
    val readTimeout: Long? = null
) {

    public val headers = mutableMapOf<String, String>()

    public val urlParameters = mutableMapOf<String, String>()

    public val body: JsonObject? = null

    public fun headerForwardedFor(ipAddress: String) {
        headers[KeyForwardedFor] = ipAddress
    }

    public fun headerAlgoliaUserId(userId: UserID) {
        headers[KeyAlgoliaUserID] = userId.raw
    }
}