package com.algolia.search.configuration

import com.algolia.search.serialize.JsonNonStrict
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.features.UserAgent
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod

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
    defaultRequest {
        configuration.defaultHeaders?.let {
            it.forEach { (key, value) -> header(key, value) }
        }
        if (method.canCompress()) {
            compressionHeader(configuration.compression)
        }
    }
}

internal fun HttpMethod.canCompress(): Boolean {
    return this == HttpMethod.Post || this == HttpMethod.Put
}

internal fun HttpRequestBuilder.compressionHeader(compression: Compression) {
    when (compression) {
        Compression.Gzip -> header(HttpHeaders.ContentEncoding, "gzip")
        else -> Unit
    }
}
