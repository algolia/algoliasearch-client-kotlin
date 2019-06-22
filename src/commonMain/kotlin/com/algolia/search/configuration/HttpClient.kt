package com.algolia.search.configuration

import BuildConfig
import com.algolia.search.model.response.ResponseABTest
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.synonym.Synonym
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
import kotlinx.serialization.json.JsonObjectSerializer

internal fun Configuration.getHttpClient() = engine?.let {
    HttpClient(it) { configure(this@getHttpClient) }
} ?: HttpClient { configure(this@getHttpClient) }

internal fun HttpClientConfig<*>.configure(configuration: Configuration) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(JsonNonStrict)
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
        level = configuration.logLevel
        logger = Logger.SIMPLE
    }
    install(UserAgent) {
        agent = "Algolia for Kotlin (${BuildConfig.version})"
    }
    configuration.defaultHeaders?.let {
        install(DefaultRequest) {
            it.forEach { (key, value) -> header(key, value) }
        }
    }
}