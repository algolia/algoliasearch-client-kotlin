package com.algolia.search.saas.client


fun requestOptions(init: RequestOptions.() -> Unit): RequestOptions {
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
        headers["X-Forwarded-For"] = ipAddress
        return this
    }

    fun headerAlgoliaUserId(userId: String): RequestOptions {
        headers["X-Algolia-UserID"] = userId
        return this
    }

    fun urlParameter(key: String, value: String): RequestOptions {
        urlParameters[key] = value
        return this
    }
}