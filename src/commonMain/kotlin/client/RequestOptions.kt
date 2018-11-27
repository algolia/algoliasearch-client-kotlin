package client


data class RequestOptions(
    val readTimeout: Long? = null,
    val searchTimeout: Long? = null
) {

    val headers = mutableMapOf<String, String>()

    val urlParameters = mutableMapOf<String, String>()

    fun setHeader(key: String, value: String): RequestOptions {
        headers[key] = value
        return this
    }

    fun setHeaderForwardedFor(ipAddress: String): RequestOptions {
        headers["X-Forwarded-For"] = ipAddress
        return this
    }

    fun setAlgoliaUserId(userId: String): RequestOptions {
        headers["X-Algolia-UserID"] = userId
        return this
    }

    fun setUrlParameters(key: String, value: String): RequestOptions {
        urlParameters[key] = value
        return this
    }
}