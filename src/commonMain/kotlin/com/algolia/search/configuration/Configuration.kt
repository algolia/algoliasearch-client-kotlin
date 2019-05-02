package com.algolia.search.configuration

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.RequestOptions
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel


/**
 * Configuration used by a client.
 */
public interface Configuration {

    /**
     * [ApplicationID] to target. Is passed as a HTTP header.
     */
    val applicationID: ApplicationID
    /**
     * [APIKey] for a given [ApplicationID]. Is passed as a HTTP header.
     * To maintain security, never use your Admin [APIKey] on your front end or share it with anyone.
     * In your front end, use the Search-only [APIKey] or any other key that has search-only rights.
     */
    val apiKey: APIKey
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
     * An optional [HttpClientEngine] used by Ktor that can be configured.
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