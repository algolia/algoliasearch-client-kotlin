package com.algolia.search.saas.client

import com.algolia.search.saas.model.UserID
import com.algolia.search.saas.serialize.KeyAlgoliaUserID
import com.algolia.search.saas.serialize.KeyForwardedFor


internal fun requestOptions(init: RequestOptions.() -> Unit): RequestOptions {
    return RequestOptions().apply(init)
}

data class RequestOptions(
    val writeTimeout: Long? = null,
    val readTimeout: Long? = null
) {

    val headers = mutableMapOf<String, String>()

    val urlParameters = mutableMapOf<String, String>()

    fun header(key: String, value: String): RequestOptions {
        headers[key] = value
        return this
    }

    fun headerForwardedFor(ipAddress: String): RequestOptions {
        headers[KeyForwardedFor] = ipAddress
        return this
    }

    fun headerAlgoliaUserId(userId: UserID): RequestOptions {
        headers[KeyAlgoliaUserID] = userId.raw
        return this
    }

    fun urlParameter(key: String, value: String): RequestOptions {
        urlParameters[key] = value
        return this
    }
}