package com.algolia.search.configuration

import com.algolia.search.serialize.JsonNonStrict
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.UserAgent
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.header


internal fun Configuration.getHttpClient() = engine?.let {
    HttpClient(it) { configure(this@getHttpClient) }
} ?: HttpClient { configure(this@getHttpClient) }

internal fun HttpClientConfig<*>.configure(configuration: Configuration) {
    configuration.httpClientConfig?.invoke(this)
    install(JsonFeature) {
        serializer = KotlinxSerializer(JsonNonStrict)
    }
    install(Logging) {
        level = configuration.logLevel
        logger = Logger.SIMPLE
    }
    install(UserAgent) {
        agent = clientUserAgent(AlgoliaSearchClient.version)
    }
    configuration.defaultHeaders?.let {
        install(DefaultRequest) {
            it.forEach { (key, value) -> header(key, value) }
        }
    }
}