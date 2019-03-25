package com.algolia.search.configuration

import com.algolia.search.model.response.ResponseABTest
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.synonym.Synonym
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.header
import kotlinx.serialization.json.JsonObjectSerializer

internal fun HttpClientEngine?.httpClient(
    logLevel: LogLevel,
    defaultHeaders: Map<String, String>?
): HttpClient {
    return if (this != null) {
        val config = HttpClientConfig<HttpClientEngineConfig>().apply { configure(logLevel, defaultHeaders) }

        HttpClient(this, config)
    } else HttpClient { configure(logLevel, defaultHeaders) }
}

internal fun HttpClientConfig<*>.configure(
    logLevel: LogLevel,
    defaultHeaders: Map<String, String>?
) {
    install(JsonFeature) {
        serializer = KotlinxSerializer() // TODO Non strict json
            .also {
                it.register(ResponseBatches)
                it.register(Synonym)
                it.register(JsonObjectSerializer)
                it.register(Rule.serializer())
                it.register(CreationAPIKey.serializer())
                it.register(RevisionIndex.serializer())
                it.register(ResponseABTest)
            }
    }
    install(Logging) {
        level = logLevel
        logger = Logger.SIMPLE
    }
    if (defaultHeaders != null)
        install(DefaultRequest) {
            defaultHeaders.forEach { (key, value) -> header(key, value) }
        }
}