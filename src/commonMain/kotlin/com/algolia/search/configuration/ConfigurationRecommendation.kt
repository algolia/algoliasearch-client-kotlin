@file:Suppress("FunctionName")

package com.algolia.search.configuration

import com.algolia.search.client.ClientRecommendation
import com.algolia.search.configuration.internal.ConfigurationRecommendationImpl
import com.algolia.search.configuration.internal.DEFAULT_LOG_LEVEL
import com.algolia.search.configuration.internal.DEFAULT_READ_TIMEOUT
import com.algolia.search.configuration.internal.DEFAULT_WRITE_TIMEOUT
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.internal.hosts
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel

/**
 * Configuration used by [ClientRecommendation].
 */
public interface ConfigurationRecommendation : Configuration, Credentials {

    /**
     * Recommendation region.
     */
    public val region: Region.Recommendation
}

/**
 * Create a [ConfigurationAnalytics] instance.
 *
 * @param applicationID application ID
 * @param apiKey API key
 * @param region recommendation region
 * @param writeTimeout write timout
 * @param readTimeout read timeout
 * @param logLevel logging level
 * @param hosts recommendation region hosts
 * @param defaultHeaders default headers
 * @param engine http client engine
 * @param httpClientConfig http client configuration
 */
public fun ConfigurationRecommendation(
    applicationID: ApplicationID,
    apiKey: APIKey,
    region: Region.Recommendation,
    writeTimeout: Long = DEFAULT_WRITE_TIMEOUT,
    readTimeout: Long = DEFAULT_READ_TIMEOUT,
    logLevel: LogLevel = DEFAULT_LOG_LEVEL,
    hosts: List<RetryableHost> = region.hosts,
    defaultHeaders: Map<String, String>? = null,
    engine: HttpClientEngine? = null,
    httpClientConfig: (HttpClientConfig<*>.() -> Unit)? = null,
): ConfigurationRecommendation = ConfigurationRecommendationImpl(
    applicationID = applicationID,
    apiKey = apiKey,
    region = region,
    writeTimeout = writeTimeout,
    readTimeout = readTimeout,
    logLevel = logLevel,
    hosts = hosts,
    defaultHeaders = defaultHeaders,
    engine = engine,
    httpClientConfig = httpClientConfig
)
