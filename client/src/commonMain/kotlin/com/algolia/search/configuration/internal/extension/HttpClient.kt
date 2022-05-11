package com.algolia.search.configuration.internal.extension

import com.algolia.search.configuration.AlgoliaSearchClient
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.clientUserAgent
import com.algolia.search.serialize.internal.JsonNonStrict
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter

internal fun Configuration.getHttpClient() = engine?.let {
    HttpClient(it) { configure(this@getHttpClient) }
} ?: HttpClient { configure(this@getHttpClient) }

internal fun HttpClientConfig<*>.configure(configuration: Configuration) {
    configuration.httpClientConfig?.invoke(this)
    install(ContentNegotiation) {
        register(ContentType.Application.Json, KotlinxSerializationConverter(JsonNonStrict))
    }
    installLogging(configuration.logLevel)
    install(UserAgent) {
        agent = clientUserAgent(AlgoliaSearchClient.version)
    }
    install(HttpTimeout)
    defaultRequest {
        configuration.defaultHeaders?.let {
            it.forEach { (key, value) -> header(key, value) }
        }
    }
    install(ClientCompression) {
        compression = configuration.compression
    }
}

/**
 * Installs [Logging] if logging level is superior to [LogLevel.NONE].
 */
private fun HttpClientConfig<*>.installLogging(logLevel: LogLevel) {
    if (LogLevel.NONE == logLevel) return
    install(Logging) {
        level = logLevel
        logger = Logger.SIMPLE
    }
}
