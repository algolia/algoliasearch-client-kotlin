package client

import client.data.ApiKey
import client.data.ApplicationId
import client.data.Index
import client.data.MultipleQueriesStrategy
import client.host.RetryLogic
import client.query.IndexQuery
import client.query.Query
import client.response.*
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

    private fun Index.pathIndexes(suffix: String = ""): String {
        return "/1/indexes/${encode().string}" + suffix
    }

    suspend fun getListIndexes(requestOptions: RequestOptions? = null): ListIndexes {
        return read.retry(requestOptions.computedReadTimeout, "/1/indexes") { path ->
            httpClient.get<ListIndexes>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    internal suspend fun search(index: Index, requestOptions: RequestOptions? = null): Hits {
        return read.retry(requestOptions.computedReadTimeout, index.pathIndexes()) { path ->
            httpClient.get<Hits>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    suspend fun search(index: Index, query: Query? = null, requestOptions: RequestOptions? = null): Hits {
        return read.retry(requestOptions.computedReadTimeout, index.pathIndexes("/query")) { path ->
            httpClient.post<Hits>(path) {
                setRequestOptions(requestOptions)
                setBody(query)
            }
        }
    }

    suspend fun browse(index: Index, query: Query? = null, requestOptions: RequestOptions? = null): Hits {
        return read.retry(requestOptions.computedReadTimeout, index.pathIndexes("/browse")) { path ->
            httpClient.post<Hits>(path) {
                setRequestOptions(requestOptions)
                setBody(query)
            }
        }
    }

    suspend fun browse(index: Index, cursor: String, requestOptions: RequestOptions? = null): Hits {
        return read.retry(requestOptions.computedReadTimeout, index.pathIndexes("/browse")) { path ->
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

    suspend fun getSettings(index: Index): Settings {
        return read.retry(readTimeout, index.pathIndexes("/settings")) { path ->
            Settings.deserialize(JsonTreeParser.parse(httpClient.get(path)))!!
        }
    }

    suspend fun setSettings(index: Index, settings: Settings, forwardToReplicas: Boolean = false): Task {
        return write.retry(writeTimeout, index.pathIndexes("/settings")) { path ->
            httpClient.put<Task>(path) {
                body = Settings.serialize(settings).toString()
                parameter(KeyForwardToReplicas, forwardToReplicas)
            }
        }
    }

    suspend fun searchForFacetValue(
        index: Index,
        facetName: String,
        query: Query? = null,
        facetQuery: String? = null,
        maxFacetHits: Int? = null,
        requestOptions: RequestOptions? = null
    ): FacetHits {
        return read.retry(
            requestOptions.computedReadTimeout,
            index.pathIndexes("/facets/$facetName/query")
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