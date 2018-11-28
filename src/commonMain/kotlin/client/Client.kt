package client

import client.query.Query
import client.response.FacetHits
import client.response.Hits
import client.response.ListIndexes
import client.serialize.toMap
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.coroutines.withTimeout


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
                it.register<FacetHits>()
                it.register<FacetHits.Item>()
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

    private fun searchTimeout(requestOptions: RequestOptions?): Long {
        return requestOptions?.searchTimeout ?: searchTimeout
    }

    private fun readTimeout(requestOptions: RequestOptions?): Long {
        return requestOptions?.readTimeout ?: readTimeout
    }

    private fun pathIndexes(index: StringUTF8): String {
        return "$host/1/indexes/${index.string}"
    }

    suspend fun getListIndexes(requestOptions: RequestOptions? = null): ListIndexes {
        return withTimeout(readTimeout(requestOptions)) {
            httpClient.get<ListIndexes>("$host/1/indexes") {
                setRequestOptions(requestOptions)
            }
        }
    }

    private suspend fun search(index: Index, requestOptions: RequestOptions? = null): Hits {
        return withTimeout(searchTimeout(requestOptions)) {
            httpClient.get<Hits>(pathIndexes(index.encode())) {
                setRequestOptions(requestOptions)
            }
        }
    }

    suspend fun search(index: Index, query: Query? = null, requestOptions: RequestOptions? = null): Hits {
        return withTimeout(searchTimeout(requestOptions)) {
            httpClient.post<Hits>(pathIndexes(index.encode()) + "/query") {
                setRequestOptions(requestOptions)
                setQuery(query)
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
        return withTimeout(searchTimeout(requestOptions)) {
            httpClient.post<FacetHits>(pathIndexes(index.encode()) + "/facets/$facetName/query") {
                setRequestOptions(requestOptions)
                val map = query?.toMap() ?: mutableMapOf()

                maxFacetHits?.let { map["maxFacetHits"] = it }
                facetQuery?.let { map["facetQuery"] = it }
                setBody(map)
            }
        }
    }

    suspend fun browse(index: Index, query: Query? = null, requestOptions: RequestOptions? = null): Hits {
        return withTimeout(searchTimeout(requestOptions)) {
            httpClient.post<Hits>(pathIndexes(index.encode()) + "/browse") {
                setRequestOptions(requestOptions)
                setQuery(query)
            }
        }
    }

    suspend fun browse(index: Index, cursor: String, requestOptions: RequestOptions? = null): Hits {
        return withTimeout(searchTimeout(requestOptions)) {
            httpClient.get<Hits>(pathIndexes(index.encode()) + "/browse") {
                setRequestOptions(requestOptions)
                parameter("cursor", cursor)
            }
        }
    }
}