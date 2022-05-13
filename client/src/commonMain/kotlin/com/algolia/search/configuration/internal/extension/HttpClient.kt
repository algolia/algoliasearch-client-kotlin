package com.algolia.search.configuration.internal.extension

import com.algolia.search.configuration.AlgoliaSearchClient
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.clientUserAgent
import com.algolia.search.logging.LogLevel
import com.algolia.search.serialize.internal.JsonNonStrict
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json

internal fun Configuration.getHttpClient() = engine?.let {
    HttpClient(it) { configure(this@getHttpClient) }
} ?: HttpClient { configure(this@getHttpClient) }

internal fun HttpClientConfig<*>.configure(configuration: Configuration) {
    // Custom configuration
    configuration.httpClientConfig?.invoke(this)

    // Content negotiation and serialization
    install(ContentNegotiation) { json(JsonNonStrict) }

    // Logging
    installLogging(configuration.logLevel)

    // User agent
    install(UserAgent) {
        agent = clientUserAgent(AlgoliaSearchClient.version)
    }

    // Timeout
    install(HttpTimeout)

    // Gzip Compression
    install(ClientCompression) {
        compression = configuration.compression
    }

    // Defaults
    defaultRequest {
        configuration.defaultHeaders?.forEach(::header)
    }

    // Enable default (2XX) validation
    expectSuccess = true
}

/**
 * Installs [Logging] if logging level is superior to [LogLevel.NONE].
 */
private fun HttpClientConfig<*>.installLogging(logLevel: LogLevel) {
    if (LogLevel.None == logLevel) return
    install(Logging) {
        level = logLevel.toKtorLogLevel()
        logger = Logger.SIMPLE
    }
}
