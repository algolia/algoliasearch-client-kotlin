package com.algolia.search.configuration

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.internal.searchHosts
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel

/**
 * Configuration used by [ClientSearch].
 */
public data class ConfigurationSearch(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey,
    override val writeTimeout: Long = defaultWriteTimeout,
    override val readTimeout: Long = defaultReadTimeout,
    override val logLevel: LogLevel = defaultLogLevel,
    override val hosts: List<RetryableHost> = applicationID.searchHosts,
    override val defaultHeaders: Map<String, String>? = null,
    override val engine: HttpClientEngine? = null,
    override val httpClientConfig: (HttpClientConfig<*>.() -> Unit)? = null,
    override val compression: Compression = Compression.None
) : Configuration, Credentials {

    override val httpClient: HttpClient = getHttpClient()
}
