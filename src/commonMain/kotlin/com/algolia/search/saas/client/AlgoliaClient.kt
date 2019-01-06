package com.algolia.search.saas.client

import com.algolia.search.saas.data.ApiKey
import com.algolia.search.saas.data.ApplicationId
import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.ListIndexes
import com.algolia.search.saas.host.RetryLogic
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.get
import kotlinx.serialization.json.Json


class AlgoliaClient(
    override val applicationId: ApplicationId,
    override val apiKey: ApiKey,
    override val writeTimeout: Long = 30000,
    override val readTimeout: Long = 2000,
    override val logLevel: LogLevel = LogLevel.BODY
) : Configuration {

    internal val client = object : Client {

        override val httpClient = HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json.nonstrict)
            }
            install(DefaultRequest) {
                setApplicationId(applicationId)
                setApiKey(apiKey)
            }
            install(Logging) {
                level = logLevel
                logger = Logger.SIMPLE
            }
        }
        override val read = RetryLogic(applicationId, RetryLogic.Type.Read)
        override val write = RetryLogic(applicationId, RetryLogic.Type.Write)
    }

    private val indexes = mutableMapOf<IndexName, Index>()

    fun getIndex(indexName: IndexName): Index {
        return indexes.getOrPut(indexName) {
            Index(this, indexName)
        }
    }

    suspend fun listIndexes(requestOptions: RequestOptions? = null): ListIndexes {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, "/1/indexes") { path ->
                httpClient.get<ListIndexes>(path) {
                    setRequestOptions(requestOptions)
                }
            }
        }
    }
}