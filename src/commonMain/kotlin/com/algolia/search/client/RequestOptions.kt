package com.algolia.search.client

import com.algolia.search.model.UserID
import com.algolia.search.serialize.KeyAlgoliaUserID
import com.algolia.search.serialize.KeyForwardedFor
import kotlinx.serialization.json.JsonObject


data class RequestOptions(
    val writeTimeout: Long? = null,
    val readTimeout: Long? = null
) {

    val headers = mutableMapOf<String, String>()

    val urlParameters = mutableMapOf<String, String>()

    val body: JsonObject? = null

    fun headerForwardedFor(ipAddress: String) {
        headers[KeyForwardedFor] = ipAddress
    }

    fun headerAlgoliaUserId(userId: UserID) {
        headers[KeyAlgoliaUserID] = userId.raw
    }
}