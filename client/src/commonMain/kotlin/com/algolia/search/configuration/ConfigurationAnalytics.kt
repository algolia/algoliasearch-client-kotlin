@file:Suppress("FunctionName")

package com.algolia.search.configuration

import com.algolia.search.client.ClientAnalytics
import com.algolia.search.configuration.internal.ConfigurationAnalyticsImpl
import com.algolia.search.configuration.internal.DEFAULT_LOG_LEVEL
import com.algolia.search.configuration.internal.DEFAULT_READ_TIMEOUT
import com.algolia.search.configuration.internal.DEFAULT_WRITE_TIMEOUT
import com.algolia.search.logging.LogLevel
import com.algolia.search.logging.Logger
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.internal.hosts
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine

/**
 * Configuration used by [ClientAnalytics].
 */
public interface ConfigurationAnalytics : Configuration, Credentials {

    /**
     * Analytics region.
     */
    public val region: Region.Analytics
}

/**
 * Create a [ConfigurationAnalytics] instance.
 *
 * @param applicationID application ID
 * @param apiKey API key
 * @param region analytics region
 * @param writeTimeout write timout
 * @param readTimeout read timeout
 * @param logLevel logging level
 * @param hosts analytics region hosts
 * @param defaultHeaders default headers
 * @param engine http client engine
 * @param httpClientConfig http client configuration
 */
public fun ConfigurationAnalytics(
    applicationID: ApplicationID,
    apiKey: APIKey,
    region: Region.Analytics,
    writeTimeout: Long = DEFAULT_WRITE_TIMEOUT,
    readTimeout: Long = DEFAULT_READ_TIMEOUT,
    logLevel: LogLevel = DEFAULT_LOG_LEVEL,
    hosts: List<RetryableHost> = region.hosts,
    defaultHeaders: Map<String, String>? = null,
    engine: HttpClientEngine? = null,
    httpClientConfig: (HttpClientConfig<*>.() -> Unit)? = null,
    logger: Logger = Logger.Simple,
): ConfigurationAnalytics = ConfigurationAnalyticsImpl(
    applicationID = applicationID,
    apiKey = apiKey,
    region = region,
    writeTimeout = writeTimeout,
    readTimeout = readTimeout,
    logLevel = logLevel,
    hosts = hosts,
    defaultHeaders = defaultHeaders,
    engine = engine,
    httpClientConfig = httpClientConfig,
    logger = logger,
)
