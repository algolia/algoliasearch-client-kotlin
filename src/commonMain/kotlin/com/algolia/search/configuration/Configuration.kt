package com.algolia.search.configuration

import com.algolia.search.transport.RequestOptions
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel


/**
 * Configuration used by a client.
 */
public interface Configuration {

    /**
     * The timeout for each request when performing write operations (POST, PUT ..).
     */
    val writeTimeout: Long
    /**
     * The timeout for each request when performing read operations (GET).
     */
    val readTimeout: Long
    /**
     * [LogLevel] to display in the console.
     */
    val logLevel: LogLevel
    /**
     * List of hosts and back-up host used to perform a custom retry logic.
     */
    val hosts: List<RetryableHost>
    /**
     * An optional [HttpClientConfig<*>] used by Ktor for advanced HttpClient configuration.
     */
    val configuration: ((HttpClientConfig<*>) -> Unit)?
    /**
     * An optional [HttpClientEngine] to specify which HttpEngine should be used by Ktor.
     */
    val engine: HttpClientEngine?
    /**
     * The [HttpClient] used by Ktor to perform http request.
     */
    val httpClient: HttpClient
    /**
     * Default headers that should be applied to every request.
     */
    val defaultHeaders: Map<String, String>?

    /**
     * @return The timeout that should be applied depending on the [CallType] and if a custom value has been
     * configured locally by [RequestOptions].
     */
    fun RequestOptions?.getTimeout(callType: CallType): Long {
        return when (callType) {
            CallType.Read -> this?.readTimeout ?: readTimeout
            CallType.Write -> this?.writeTimeout ?: writeTimeout
        }
    }
}