package client

import client.host.Hosts
import client.response.FacetHits
import client.response.Hits
import client.response.ListIndexes
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse


class AbstractClient(
    val applicationId: ApplicationId,
    val apiKey: ApiKey,
    val readTimeout: Long = 30000,
    val searchTimeout: Long = 5000
) {

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

    private val hosts = Hosts(applicationId)

    private val RequestOptions?.computedReadTimeout get() = this?.readTimeout ?: readTimeout
    private val RequestOptions?.computedSearchTimeout get() = this?.searchTimeout ?: searchTimeout

    suspend fun getListIndexes(requestOptions: RequestOptions? = null): HttpResponse {
        return hosts.retryLogic(requestOptions.computedReadTimeout, "/1/indexes") { path ->
            httpClient.get(path)
        }
    }

//    suspend fun search(index: Index, requestOptions: RequestOptions? = null): HttpResponse {
//
//    }
//
//    suspend fun search(index: Index, query: Query? = null, requestOptions: RequestOptions? = null): HttpResponse {
//
//    }

//    suspend fun searchForFacetValue(
//        index: Index,
//        facetName: String,
//        query: Query? = null,
//        facetQuery: String? = null,
//        maxFacetHits: Int? = null,
//        requestOptions: RequestOptions? = null
//    ): HttpResponse {
//
//    }
//
//    suspend fun browse(index: Index, query: Query? = null, requestOptions: RequestOptions? = null): HttpResponse {
//
//    }
//
//    suspend fun browse(index: Index, cursor: String, requestOptions: RequestOptions? = null): HttpResponse {
//
//    }
}