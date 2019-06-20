package com.algolia.search.configuration

import com.algolia.search.client.ClientAnalytics
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.analyticsHost
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel


/**
 * Configuration used by [ClientAnalytics].
 */
public data class ConfigurationAnalytics(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey,
    override val writeTimeout: Long = defaultWriteTimeout,
    override val readTimeout: Long = defaultReadTimeout,
    override val logLevel: LogLevel = defaultLogLevel,
    override val hosts: List<RetryableHost> = listOf(analyticsHost),
    override val defaultHeaders: Map<String, String>? = null,
    override val engine: HttpClientEngine? = null
) : Configuration, Credentials {

    override val httpClient = getHttpClient()
}