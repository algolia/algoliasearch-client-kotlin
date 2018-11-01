package client

import client.query.SearchParameters
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
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
                it.setMapper(ListIndexes::class, ListIndexes.serializer())
                it.setMapper(ListIndexes.Item::class, ListIndexes.Item.serializer())
                it.setMapper(Hits::class, Hits.serializer())
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

    suspend fun getListIndexes(): ListIndexes {
        return withTimeout(readTimeout) {
            httpClient.get<ListIndexes>("$host/1/indexes")
        }
    }

    suspend fun search(index: Index): Hits {
        return withTimeout(searchTimeout) {
            httpClient.get<Hits>(pathIndexes(index.encode())) {
                url {
                    parameters["facets"] = JSON.stringify(listOf("color"))
                }
            }
        }
    }

    suspend fun searchQuery(index: Index, searchParameters: SearchParameters): Hits {
        return withTimeout(searchTimeout) {
            httpClient.post<Hits>(pathIndexes(index.encode()) + "/query") {
                body = searchParameters.stringify()
            }
        }
    }
}