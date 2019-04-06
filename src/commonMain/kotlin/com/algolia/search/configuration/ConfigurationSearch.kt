package com.algolia.search.configuration

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.searchHosts
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel


data class ConfigurationSearch(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey,
    override val writeTimeout: Long = defaultWriteTimeout,
    override val readTimeout: Long = defaultReadTimeout,
    override val logLevel: LogLevel = defaultLogLevel,
    override val hosts: List<RetryableHost> = applicationID.searchHosts,
    override val defaultHeaders: Map<String, String>? = null,
    override val engine: HttpClientEngine? = null
) : Configuration {

    override val httpClient = getHttpClient()
}