package com.algolia.search.configuration

import com.algolia.search.client.ClientPlaces
import com.algolia.search.transport.placesHosts
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel

/**
 * Configuration used by [ClientPlaces].
 */
data class ConfigurationPlaces(
    override val writeTimeout: Long = defaultWriteTimeout,
    override val readTimeout: Long = defaultReadTimeout,
    override val logLevel: LogLevel = defaultLogLevel,
    override val hosts: List<RetryableHost> = placesHosts,
    override val defaultHeaders: Map<String, String>? = null,
    override val engine: HttpClientEngine? = null,
    override val httpClientConfig: (HttpClientConfig<*>.() -> Unit)? = null
) : Configuration {

    override val compression: Compression = Compression.None
    override val httpClient = getHttpClient()
}
