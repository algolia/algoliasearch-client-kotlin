package com.algolia.search.configuration.internal

import com.algolia.search.configuration.Compression
import com.algolia.search.configuration.ConfigurationPlaces
import com.algolia.search.configuration.RetryableHost
import com.algolia.search.configuration.internal.extension.getHttpClient
import com.algolia.search.logging.LogLevel
import com.algolia.search.logging.Logger
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine

/**
 * Implementation of [ConfigurationPlaces].
 */
internal class ConfigurationPlacesImpl(
    override val writeTimeout: Long,
    override val readTimeout: Long,
    override val logLevel: LogLevel,
    override val hosts: List<RetryableHost>,
    override val defaultHeaders: Map<String, String>?,
    override val engine: HttpClientEngine?,
    override val httpClientConfig: (HttpClientConfig<*>.() -> Unit)?,
    override val logger: Logger,
) : ConfigurationPlaces {

    override val compression: Compression = Compression.None
    override val httpClient: HttpClient = getHttpClient()
}
