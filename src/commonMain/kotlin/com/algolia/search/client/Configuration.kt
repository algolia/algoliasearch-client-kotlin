package com.algolia.search.client

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import io.ktor.client.features.logging.LogLevel


data class Configuration(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey,
    override val writeTimeout: Long = 30000,
    override val readTimeout: Long = 2000,
    override val logLevel: LogLevel = LogLevel.ALL
) : ConfigurationInterface