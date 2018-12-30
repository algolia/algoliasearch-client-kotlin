package client

import client.data.*
import client.host.RetryLogic
import client.serialize.KeyFacetQuery
import client.serialize.KeyForwardToReplicas
import client.serialize.KeyMaxFacetHits
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import kotlinx.coroutines.delay
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTreeParser
import kotlinx.serialization.json.json


class Client(
    val applicationId: ApplicationId,
    val apiKey: ApiKey,
    val writeTimeout: Long = 30000,
    val readTimeout: Long = 2000,
    val logLevel: LogLevel = LogLevel.ALL
) {

    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
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

    private val read = RetryLogic(applicationId, RetryLogic.Type.Read)
    private val write = RetryLogic(applicationId, RetryLogic.Type.Write)

    private val RequestOptions?.computedWriteTimeout get() = this?.writeTimeout ?: writeTimeout
    private val RequestOptions?.computedReadTimeout get() = this?.readTimeout ?: readTimeout

    suspend fun getListIndexes(requestOptions: RequestOptions? = null): ListIndexes {
        return read.retry(requestOptions.computedReadTimeout, "/1/indexes") { path ->
            httpClient.get<ListIndexes>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    internal suspend fun search(indexName: IndexName, requestOptions: RequestOptions? = null): Hits {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes()) { path ->
            httpClient.get<Hits>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    suspend fun search(indexName: IndexName, query: Query? = null, requestOptions: RequestOptions? = null): Hits {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/query")) { path ->
            httpClient.post<Hits>(path) {
                setRequestOptions(requestOptions)
                setBody(query)
            }
        }
    }

    suspend fun browse(indexName: IndexName, query: Query? = null, requestOptions: RequestOptions? = null): Hits {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/browse")) { path ->
            httpClient.post<Hits>(path) {
                setRequestOptions(requestOptions)
                setBody(query)
            }
        }
    }

    suspend fun browse(indexName: IndexName, cursor: String, requestOptions: RequestOptions? = null): Hits {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/browse")) { path ->
            httpClient.get<Hits>(path) {
                setRequestOptions(requestOptions)
                parameter("cursor", cursor)
            }
        }
    }

    suspend fun multipleQueries(
        queries: Collection<IndexQuery>,
        strategy: MultipleQueriesStrategy = MultipleQueriesStrategy.None,
        requestOptions: RequestOptions? = null
    ): MultipleHits {
        return read.retry(requestOptions.computedReadTimeout, "/1/indexes/*/queries") { path ->
            httpClient.post<MultipleHits>(path) {
                setRequestOptions(requestOptions)
                setQueries(queries, strategy)
            }
        }
    }

    suspend fun getSettings(indexName: IndexName): Settings {
        return read.retry(readTimeout, indexName.pathIndexes("/settings")) { path ->
            Settings.deserialize(JsonTreeParser.parse(httpClient.get(path)))!!
        }
    }

    suspend fun setSettings(
        indexName: IndexName,
        settings: Settings,
        vararg resetToDefault: SettingsKey,
        forwardToReplicas: Boolean = false
    ): TaskSettings {
        return write.retry(writeTimeout, indexName.pathIndexes("/settings")) { path ->
            httpClient.put<TaskSettings>(path) {
                val map = Settings.serialize(settings).toMutableMap().apply {
                    resetToDefault.forEach {
                        put(it.raw, JsonNull)
                    }
                }
                body = JsonObject(map).toString()
                parameter(KeyForwardToReplicas, forwardToReplicas)
            }
        }
    }

    suspend fun getTask(indexName: IndexName, taskId: Long): TaskInfo {
        return read.retry(writeTimeout, indexName.pathIndexes("/task/$taskId")) { path ->
            TaskInfo.deserialize(JsonTreeParser.parse(httpClient.get(path)))!!
        }
    }

    suspend fun wait(indexName: IndexName, taskId: Long): TaskInfo {
        val timeToWait = 10000L
        var attempt = 1

        while (true) {
            getTask(indexName, taskId).let {
                if (it.status == TaskStatus.Published) return it
            }
            delay(timeToWait * attempt)
            attempt++
        }
    }

    suspend fun searchForFacetValue(
        indexName: IndexName,
        facetName: String,
        query: Query? = null,
        facetQuery: String? = null,
        maxFacetHits: Int? = null,
        requestOptions: RequestOptions? = null
    ): FacetHits {
        return read.retry(
            requestOptions.computedReadTimeout,
            indexName.pathIndexes("/facets/$facetName/query")
        ) { path ->
            httpClient.post<FacetHits>(path) {
                setRequestOptions(requestOptions)
                val extraParams = json {
                    maxFacetHits?.let { KeyMaxFacetHits to it }
                    facetQuery?.let { KeyFacetQuery to it }
                }

                body = if (query != null) {
                    val serialize = Query.serialize(query)
                    val map = serialize.toMutableMap()

                    map.putAll(extraParams)
                    JsonObject(map)
                } else {
                    extraParams
                }.toString()
            }
        }
    }
}