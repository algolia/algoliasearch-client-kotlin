package com.algolia.search.configuration

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.hosts
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel


data class ConfigurationSearch(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey,
    override val writeTimeout: Long = 30000,
    override val readTimeout: Long = 2000,
    override val logLevel: LogLevel = LogLevel.ALL,
    override val hosts: List<RetryableHost> = applicationID.hosts,
    override val engine: HttpClientEngine? = null,
    override val httpClient: HttpClient = engine.httpClient(logLevel)
) : Configuration