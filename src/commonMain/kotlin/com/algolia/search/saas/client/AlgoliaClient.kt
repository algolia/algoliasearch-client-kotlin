package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.host.RetryLogic
import com.algolia.search.saas.serialize.KeyRequests
import com.algolia.search.saas.serialize.KeyResults
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import kotlinx.serialization.list


class AlgoliaClient(
    override val applicationID: ApplicationID,
    override val apiKey: ApiKey,
    override val writeTimeout: Long = 30000,
    override val readTimeout: Long = 2000,
    override val logLevel: LogLevel = LogLevel.BODY
) : Configuration,
    EndpointMultipleIndices {

    internal val client = object : Client {

        override val httpClient = HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json.nonstrict)
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

    private val indexes = mutableMapOf<IndexName, Index>()

    fun getIndex(indexName: IndexName): Index {
        return indexes.getOrPut(indexName) {
            Index(this, indexName)
        }
    }

    override suspend fun listIndexes(requestOptions: RequestOptions?): ListIndexes {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, "/1/indexes") { path ->
                httpClient.get<ListIndexes>(path) {
                    setRequestOptions(requestOptions)
                }
            }
        }
    }

    override suspend fun getObjects(
        request: RequestObjects,
        vararg additionalRequests: RequestObjects,
        requestOptions: RequestOptions?
    ): List<JsonObject> {
        return client.run {
            val requests = Json.plain.toJson(listOf(request) + additionalRequests, RequestObjects.list)
            val json = json { KeyRequests to requests }

            read.retry(requestOptions.computedReadTimeout, "/1/indexes/*/objects") { path ->
                httpClient.post<String>(path) {
                    setRequestOptions(requestOptions)
                    body = json.toString()
                }.let { Json.plain.parseJson(it).jsonObject.getArray(KeyResults).map { it.jsonObject } }
            }
        }
    }
}