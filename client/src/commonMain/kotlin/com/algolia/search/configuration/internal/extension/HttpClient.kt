package com.algolia.search.configuration.internal.extension

import com.algolia.search.configuration.Configuration
import com.algolia.search.logging.LogLevel
import com.algolia.search.logging.Logger
import com.algolia.search.logging.internal.toKtorLogger
import com.algolia.search.serialize.internal.JsonNonStrict
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
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
    installLogging(configuration.logLevel, configuration.logger)

    // Algolia user agent
    install(AlgoliaAgent)

    // Timeout
    install(HttpTimeout)

    // Gzip Compression
    install(ClientCompression) {
        compression = configuration.compression
    }

    // Defaults
    defaultRequest {
        configuration.defaultHeaders?.forEach { (key, value) -> header(key, value) }
    }

    // Enable default (2XX) validation
    expectSuccess = true
}

/**
 * Installs [Logging] if logging level is superior to [LogLevel.NONE].
 */
private fun HttpClientConfig<*>.installLogging(logLevel: LogLevel, customLogger: Logger) {
    if (LogLevel.None == logLevel) return
    install(Logging) {
        level = logLevel.toKtorLogLevel()
        logger = customLogger.toKtorLogger()
    }
}
