package com.algolia.search.client

import com.algolia.search.endpoint.ConfigurableEndpoints
import com.algolia.search.host.RetryLogic
import com.algolia.search.model.queryrule.QueryRule
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.response.ResponseBatches
import com.algolia.search.response.creation.CreationAPIKey
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObjectSerializer


internal class APIWrapper(
    configuration: Configuration,
    engine: HttpClientEngine? = null,
    override val endpoints: ConfigurableEndpoints
) : Client,
    ConfigurationInterface by configuration {

    private val selected = engine?.let { HttpClient(it) } ?: HttpClient()

    override val httpClient = selected.config {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.nonstrict)
                .also {
                    it.register(ResponseBatches)
                    it.register(Synonym)
                    it.register(JsonObjectSerializer)
                    it.register(QueryRule.serializer())
                    it.register(CreationAPIKey.serializer())
                }
        }
        install(DefaultRequest) {
            setApplicationId(applicationID)
            setApiKey(apiKey)
        }
        install(Logging) {
            level = logLevel
            logger = Logger.SIMPLE
        }
    }

    override val read = RetryLogic(configuration.applicationID, RetryLogic.Type.Read)
    override val write = RetryLogic(configuration.applicationID, RetryLogic.Type.Write)
}