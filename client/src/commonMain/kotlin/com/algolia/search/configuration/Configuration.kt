package com.algolia.search.configuration

import com.algolia.search.logging.LogLevel
import com.algolia.search.logging.Logger
import com.algolia.search.transport.RequestOptions
import com.algolia.search.util.Closeable
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine

/**
 * Configuration used by a client.
 */
public interface Configuration : Closeable {

    /**
     * The timeout for each request when performing write operations (POST, PUT ..).
     */
    public val writeTimeout: Long

    /**
     * The timeout for each request when performing read operations (GET).
     */
    public val readTimeout: Long

    /**
     * [LogLevel] to display in the console.
     */
    public val logLevel: LogLevel

    /**
     * List of hosts and back-up host used to perform a custom retry logic.
     */
    public val hosts: List<RetryableHost>

    /**
     * An optional [HttpClientConfig<*>] used by Ktor for advanced HttpClient httpClientConfig.
     */
    public val httpClientConfig: ((HttpClientConfig<*>) -> Unit)?

    /**
     * An optional [HttpClientEngine] to specify which HttpEngine should be used by Ktor.
     */
    public val engine: HttpClientEngine?

    /**
     * The [HttpClient] used by Ktor to perform http request.
     */
    public val httpClient: HttpClient

    /**
     * Default headers that should be applied to every request.
     */
    public val defaultHeaders: Map<String, String>?

    /**
     * The type of [Compression] to use for POST or PUT requests.
     */
    public val compression: Compression

    /**
     * @return The timeout that should be applied depending on the [CallType] and if a custom value has been
     * configured locally by [RequestOptions].
     */
    public fun RequestOptions?.getTimeout(callType: CallType): Long {
        return when (callType) {
            CallType.Read -> this?.readTimeout ?: readTimeout
            CallType.Write -> this?.writeTimeout ?: writeTimeout
        }
    }

    public val logger: Logger

    override fun close() {
        httpClient.close()
    }
}
