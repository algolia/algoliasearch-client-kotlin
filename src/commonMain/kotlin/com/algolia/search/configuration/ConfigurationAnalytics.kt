package com.algolia.search.configuration

import com.algolia.search.client.ClientAnalytics
import com.algolia.search.configuration.Region.Analytics.US
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.hosts
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel

/**
 * Configuration used by [ClientAnalytics].
 */
public data class ConfigurationAnalytics(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey,
    val region: Region.Analytics,
    override val writeTimeout: Long = defaultWriteTimeout,
    override val readTimeout: Long = defaultReadTimeout,
    override val logLevel: LogLevel = defaultLogLevel,
    override val hosts: List<RetryableHost> = region.hosts,
    override val defaultHeaders: Map<String, String>? = null,
    override val engine: HttpClientEngine? = null,
    override val httpClientConfig: (HttpClientConfig<*>.() -> Unit)? = null
) : Configuration, Credentials {

    @Deprecated(
        message = "Use default constructor and explicitly specify an analytics region",
        level = DeprecationLevel.WARNING
    )
    constructor(
        applicationID: ApplicationID,
        apiKey: APIKey,
        writeTimeout: Long = defaultWriteTimeout,
        readTimeout: Long = defaultReadTimeout,
        logLevel: LogLevel = defaultLogLevel,
        hosts: List<RetryableHost>? = null,
        defaultHeaders: Map<String, String>? = null,
        engine: HttpClientEngine? = null,
        httpClientConfig: (HttpClientConfig<*>.() -> Unit)? = null,
        region: Region.Analytics = US
    ) : this(
        applicationID, apiKey, region, writeTimeout, readTimeout, logLevel,
        hosts ?: region.hosts, defaultHeaders, engine, httpClientConfig
    )

    override val compression: Compression = Compression.None
    override val httpClient = getHttpClient()
}
