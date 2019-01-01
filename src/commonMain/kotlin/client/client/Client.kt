package client.client

import client.data.*
import client.host.RetryLogic
import client.serialize.*
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.coroutines.delay
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.json
import kotlinx.serialization.list


class Client(
    val applicationId: ApplicationId,
    val apiKey: ApiKey,
    val writeTimeout: Long = 30000,
    val readTimeout: Long = 2000,
    val logLevel: LogLevel = LogLevel.ALL
) {

    internal val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(JSON.nonstrict)
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

    fun initIndex(indexName: IndexName): Index {
        return indexes.getOrPut(indexName) {
            Index(this, indexName)
        }
    }

    suspend fun listIndexes(requestOptions: RequestOptions? = null): ListIndexes {
        return read.retry(requestOptions.computedReadTimeout, "/1/indexes") { path ->
            httpClient.get<ListIndexes>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    private suspend fun copyOrMove(
        indexName: IndexName,
        destination: IndexName,
        key: String,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions?
    ): Task {
        return write.retry(writeTimeout, indexName.pathIndexes("/operation")) { path ->
            httpClient.post<Task>(path) {
                setRequestOptions(requestOptions)
                body = json {
                    KeyOperation to key
                    KeyDestination to destination.raw
                    scopes?.let { KeyScope to JSON.stringify(client.data.Scope.list, it) }
                }.toString()
            }
        }
    }

    suspend fun copyIndex(
        indexName: IndexName,
        destination: IndexName,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions? = null
    ): Task {
        return copyOrMove(indexName, destination, KeyCopy, scopes, requestOptions)
    }

    suspend fun moveIndex(indexName: IndexName, destination: IndexName, requestOptions: RequestOptions? = null): Task {
        return copyOrMove(indexName, destination, KeyMove, requestOptions = requestOptions)
    }

    suspend fun deleteIndex(indexName: IndexName, requestOptions: RequestOptions? = null): TaskDelete {
        return write.retry(writeTimeout, indexName.pathIndexes()) { path ->
            httpClient.delete<TaskDelete>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    suspend fun getTask(indexName: IndexName, taskId: Long): TaskInfo {
        return read.retry(readTimeout, indexName.pathIndexes("/task/$taskId")) { path ->
            httpClient.get<TaskInfo>(path)
        }
    }

    suspend fun getTask(indexName: IndexName, taskId: TaskId): TaskInfo {
        return getTask(indexName, taskId.taskID)
    }

    suspend fun wait(indexName: IndexName, taskId: TaskId): TaskInfo {
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
}