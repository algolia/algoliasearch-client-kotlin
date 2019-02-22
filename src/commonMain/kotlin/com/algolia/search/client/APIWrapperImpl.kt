package com.algolia.search.client

import com.algolia.search.host.RetryLogic
import com.algolia.search.host.readHosts
import com.algolia.search.host.writeHosts
import com.algolia.search.model.response.ResponseABTest
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.synonym.Synonym
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import kotlinx.serialization.json.JsonObjectSerializer


internal class APIWrapperImpl(
    configuration: Configuration,
    engine: HttpClientEngine? = null
) : APIWrapper,
    ConfigurationInterface by configuration {

    // TODO test with proguard
    private val selected = engine?.let { HttpClient(it) } ?: HttpClient()

    override val httpClient = selected.config {
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
        install(DefaultRequest) {
            setApplicationId(applicationID)
            setApiKey(apiKey)
        }
        install(Logging) {
            level = logLevel
            logger = Logger.SIMPLE // Todo define production (and configurable) Logger
        }
    }

    override val read = RetryLogic(configuration.readHosts())
    override val write = RetryLogic(configuration.writeHosts())
}