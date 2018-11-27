package client

import client.query.Query
import client.query.stringify
import client.response.Hits
import client.response.ListIndexes
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.*
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.JSON


class Client(
    val applicationId: ApplicationId,
    val apiKey: ApiKey,
    val readTimeout: Long = 30000,
    val searchTimeout: Long = 5000
) {

    private val host = "https://${applicationId.string}-dsn.algolia.net"
    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer().also {
                it.register<ListIndexes>()
                it.register<ListIndexes.Item>()
                it.register<Hits>()
            }
        }
        install(DefaultRequest) {
            setApplicationId(applicationId)
            setApiKey(apiKey)
        }
    }

    private fun pathIndexes(index: StringUTF8): String {
        return "$host/1/indexes/${index.string}"
    }

    suspend fun getListIndexes(requestOptions: RequestOptions? = null): ListIndexes {
        return withTimeout(requestOptions?.readTimeout ?: readTimeout) {
            httpClient.get<ListIndexes>("$host/1/indexes") {
                setRequestOptions(requestOptions)
            }
        }
    }

    private suspend fun search(index: Index, requestOptions: RequestOptions? = null): Hits {
        return withTimeout(requestOptions?.searchTimeout ?: searchTimeout) {
            httpClient.get<Hits>(pathIndexes(index.encode())) {
                setRequestOptions(requestOptions)
            }
        }
    }

    suspend fun search(index: Index, query: Query, requestOptions: RequestOptions? = null): Hits {
        return withTimeout(requestOptions?.searchTimeout ?: searchTimeout) {
            httpClient.post<Hits>(pathIndexes(index.encode()) + "/query") {
                setRequestOptions(requestOptions)
                body = query.stringify()
            }
        }
    }

    suspend fun browse(index: Index, query: Query, requestOptions: RequestOptions? = null): Hits {
        return withTimeout(requestOptions?.searchTimeout ?: searchTimeout) {
            httpClient.post<Hits>(pathIndexes(index.encode()) + "/browse") {
                setRequestOptions(requestOptions)
                body = query.stringify()
            }
        }
    }

    suspend fun browse(index: Index, cursor: String, requestOptions: RequestOptions? = null): Hits {
        return withTimeout(requestOptions?.searchTimeout ?: searchTimeout) {
            httpClient.get<Hits>(pathIndexes(index.encode()) + "/browse") {
                setRequestOptions(requestOptions)
                parameter("cursor", cursor)
            }
        }
    }
}