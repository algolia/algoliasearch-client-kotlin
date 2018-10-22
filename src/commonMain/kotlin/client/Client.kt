package client

import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*

class Client(
    val applicationId: ApplicationId,
    val apiKey: ApiKey
) {

    private val host = "https://${applicationId.string}-dsn.algolia.net"
    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer().also {
                it.setMapper(ListIndexes::class, ListIndexes.serializer())
                it.setMapper(ListIndexes.Item::class, ListIndexes.Item.serializer())
            }
        }
        install(DefaultRequest) {
            setApplicationId(applicationId)
            setApiKey(apiKey)
        }
    }

    suspend fun getListIndexes(): ListIndexes {
        return httpClient.get("$host/1/indexes/")
    }
}