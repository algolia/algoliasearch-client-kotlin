package client.client

import client.RequestOptions
import client.data.ApiKey
import client.data.ApplicationId
import client.data.IndexName
import client.host.RetryLogic
import client.setApiKey
import client.setApplicationId
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE


class Client(
    val applicationId: ApplicationId,
    val apiKey: ApiKey,
    val writeTimeout: Long = 30000,
    val readTimeout: Long = 2000,
    val logLevel: LogLevel = LogLevel.ALL
) {

    internal val httpClient = HttpClient {
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

    internal val read = RetryLogic(applicationId, RetryLogic.Type.Read)
    internal val write = RetryLogic(applicationId, RetryLogic.Type.Write)

    internal val RequestOptions?.computedWriteTimeout get() = this?.writeTimeout ?: writeTimeout
    internal val RequestOptions?.computedReadTimeout get() = this?.readTimeout ?: readTimeout

    private val indexes = mutableMapOf<IndexName, Index>()

    fun get(indexName: IndexName): Index {
        return indexes.getOrPut(indexName) {
            Index(this, indexName)
        }
    }
}