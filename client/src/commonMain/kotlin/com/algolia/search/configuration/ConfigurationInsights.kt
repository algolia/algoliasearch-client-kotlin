@file:Suppress("FunctionName")

package com.algolia.search.configuration

import com.algolia.search.client.ClientInsights
import com.algolia.search.configuration.internal.ConfigurationInsightsImpl
import com.algolia.search.configuration.internal.DEFAULT_LOG_LEVEL
import com.algolia.search.configuration.internal.DEFAULT_READ_TIMEOUT
import com.algolia.search.configuration.internal.DEFAULT_WRITE_TIMEOUT
import com.algolia.search.logging.LogLevel
import com.algolia.search.logging.Logger
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.internal.insightHosts
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine

/**
 * Configuration used by [ClientInsights].
 */
public interface ConfigurationInsights : Configuration, Credentials

/**
 * Create a [ConfigurationInsights] instance.
 *
 * @param applicationID application ID
 * @param apiKey API key
 * @param writeTimeout write timout
 * @param readTimeout read timeout
 * @param logLevel logging level
 * @param hosts insights region hosts
 * @param defaultHeaders default headers
 * @param engine http client engine
 * @param httpClientConfig http client configuration
 */
public fun ConfigurationInsights(
    applicationID: ApplicationID,
    apiKey: APIKey,
    writeTimeout: Long = DEFAULT_WRITE_TIMEOUT,
    readTimeout: Long = DEFAULT_READ_TIMEOUT,
    logLevel: LogLevel = DEFAULT_LOG_LEVEL,
    hosts: List<RetryableHost> = insightHosts,
    defaultHeaders: Map<String, String>? = null,
    engine: HttpClientEngine? = null,
    httpClientConfig: (HttpClientConfig<*>.() -> Unit)? = null,
    logger: Logger = Logger.Simple,
): ConfigurationInsights = ConfigurationInsightsImpl(
    applicationID = applicationID,
    apiKey = apiKey,
    writeTimeout = writeTimeout,
    readTimeout = readTimeout,
    logLevel = logLevel,
    hosts = hosts,
    defaultHeaders = defaultHeaders,
    engine = engine,
    httpClientConfig = httpClientConfig,
    logger = logger,
)
