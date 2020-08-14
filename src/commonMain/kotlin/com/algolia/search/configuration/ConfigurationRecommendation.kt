package com.algolia.search.configuration

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.hosts
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel

public data class ConfigurationRecommendation(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey,
    val region: Region.Recommendation,
    override val writeTimeout: Long = defaultWriteTimeout,
    override val readTimeout: Long = defaultReadTimeout,
    override val logLevel: LogLevel = defaultLogLevel,
    override val hosts: List<RetryableHost> = region.hosts,
    override val defaultHeaders: Map<String, String>? = null,
    override val engine: HttpClientEngine? = null,
    override val httpClientConfig: (HttpClientConfig<*>.() -> Unit)? = null
) : Configuration, Credentials {

    override val compression: Compression = Compression.None
    override val httpClient: HttpClient = getHttpClient()
}
