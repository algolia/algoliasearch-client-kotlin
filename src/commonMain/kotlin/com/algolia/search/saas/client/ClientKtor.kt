package com.algolia.search.saas.client

import com.algolia.search.saas.data.APIKey
import com.algolia.search.saas.data.ApplicationID
import com.algolia.search.saas.data.TaskBatchOperations
import com.algolia.search.saas.host.RetryLogic
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.internal.JsonObjectSerializer

internal class ClientKtor(configuration: Configuration) : Client, Configuration by configuration {

    constructor(
        applicationID: ApplicationID,
        apiKey: APIKey,
        writeTimeout: Long = 30000,
        readTimeout: Long = 2000,
        logLevel: LogLevel = LogLevel.BODY
    ) : this(object : Configuration {
        override val applicationID = applicationID
        override val apiKey = apiKey
        override val writeTimeout = writeTimeout
        override val readTimeout = readTimeout
        override val logLevel = logLevel
    })

    override val httpClient = HttpClient {
        install(JsonFeature) {
            serializer =
                KotlinxSerializer(Json.nonstrict)
                    .also {
                        it.register(TaskBatchOperations)
                        it.register(JsonObjectSerializer)
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
    override val read = RetryLogic(applicationID, RetryLogic.Type.Read)
    override val write = RetryLogic(applicationID, RetryLogic.Type.Write)
}