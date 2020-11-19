@file:Suppress("FunctionName")

package com.algolia.search.configuration

import com.algolia.search.client.ClientAnswers
import com.algolia.search.configuration.internal.ConfigurationAnswersImpl
import com.algolia.search.configuration.internal.DEFAULT_LOG_LEVEL
import com.algolia.search.configuration.internal.DEFAULT_READ_TIMEOUT
import com.algolia.search.configuration.internal.DEFAULT_WRITE_TIMEOUT
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.internal.searchHosts
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel

/**
 * Configuration used by [ClientAnswers].
 */
public interface ConfigurationAnswers : Configuration, Credentials

/**
 * Create a [ConfigurationAnalytics] instance.
 *
 * @param applicationID application ID
 * @param apiKey API key
 * @param writeTimeout write timout
 * @param readTimeout read timeout
 * @param logLevel logging level
 * @param hosts recommendation region hosts
 * @param defaultHeaders default headers
 * @param engine http client engine
 * @param httpClientConfig http client configuration
 * @param compression request payload compression
 */
public fun ConfigurationAnswers(
    applicationID: ApplicationID,
    apiKey: APIKey,
    writeTimeout: Long = DEFAULT_WRITE_TIMEOUT,
    readTimeout: Long = DEFAULT_READ_TIMEOUT,
    logLevel: LogLevel = DEFAULT_LOG_LEVEL,
    hosts: List<RetryableHost> = applicationID.searchHosts,
    defaultHeaders: Map<String, String>? = null,
    engine: HttpClientEngine? = null,
    httpClientConfig: (HttpClientConfig<*>.() -> Unit)? = null,
    compression: Compression = Compression.None,
): ConfigurationAnswers = ConfigurationAnswersImpl(
    applicationID = applicationID,
    apiKey = apiKey,
    writeTimeout = writeTimeout,
    readTimeout = readTimeout,
    logLevel = logLevel,
    hosts = hosts,
    defaultHeaders = defaultHeaders,
    engine = engine,
    httpClientConfig = httpClientConfig,
    compression = compression
)
