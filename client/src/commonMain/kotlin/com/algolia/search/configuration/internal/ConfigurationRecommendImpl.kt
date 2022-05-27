package com.algolia.search.configuration.internal

import com.algolia.search.client.ClientRecommend
import com.algolia.search.configuration.Compression
import com.algolia.search.configuration.ConfigurationRecommend
import com.algolia.search.configuration.RetryableHost
import com.algolia.search.configuration.internal.extension.getHttpClient
import com.algolia.search.logging.LogLevel
import com.algolia.search.logging.Logger
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine

/**
 * Configuration used by [ClientRecommend].
 */
internal class ConfigurationRecommendImpl(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey,
    override val writeTimeout: Long,
    override val readTimeout: Long,
    override val logLevel: LogLevel,
    override val hosts: List<RetryableHost>,
    override val defaultHeaders: Map<String, String>?,
    override val engine: HttpClientEngine?,
    override val httpClientConfig: (HttpClientConfig<*>.() -> Unit)?,
    override val compression: Compression,
    override val logger: Logger,
) : ConfigurationRecommend {

    override val httpClient: HttpClient = getHttpClient()
}
