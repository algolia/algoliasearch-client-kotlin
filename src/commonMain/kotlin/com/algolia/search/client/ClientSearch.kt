package com.algolia.search.client

import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.endpoint.*
import com.algolia.search.helper.encodeBase64
import com.algolia.search.helper.requestOptionsBuilder
import com.algolia.search.helper.sha256
import com.algolia.search.helper.toAPIKey
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.LogType
import com.algolia.search.model.apikey.SecuredAPIKeyRestriction
import com.algolia.search.model.response.ResponseAPIKey
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.model.response.ResponseLogs
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.DeletionAPIKey
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyLength
import com.algolia.search.serialize.KeyOffset
import com.algolia.search.serialize.KeyType
import com.algolia.search.serialize.RouteLogs
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.client.features.ResponseException
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout


public class ClientSearch private constructor(
    private val transport: Transport
) :
    EndpointMultipleIndex by EndpointMultipleIndexImpl(transport),
    EndpointAPIKey by EndpointAPIKeyImpl(transport),
    EndpointMultiCluster by EndpointMulticlusterImpl(transport),
    Configuration by transport {

    public constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(Transport(ConfigurationSearch(applicationID, apiKey)))

    public constructor(
        configuration: ConfigurationSearch
    ) : this(Transport(configuration))

    public fun initIndex(indexName: IndexName): Index {
        return Index(transport, indexName)
    }

    public suspend fun List<TaskIndex>.waitAll(timeout: Long? = null): List<TaskStatus> {

        suspend fun loop(): List<TaskStatus> {
            while (true) {
                coroutineScope {
                    map { async { initIndex(it.indexName).getTask(it.taskID) } }.map { it.await().status }
                }.let {
                    if (it.all { status -> status == TaskStatus.Published }) return it
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    public suspend fun ResponseBatches.waitAll(): List<TaskStatus> {
        return tasks.waitAll()
    }

    public suspend fun CreationAPIKey.wait(timeout: Long? = null): ResponseAPIKey {

        suspend fun loop(): ResponseAPIKey {
            while (true) {
                try {
                    return getAPIKey(apiKey)
                } catch (exception: ResponseException) {
                    if (exception.response.status.value != HttpStatusCode.NotFound.value) throw exception
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    public suspend fun DeletionAPIKey.wait(timeout: Long? = null): Boolean {

        suspend fun loop(): Boolean {
            while (true) {
                try {
                    getAPIKey(apiKey)
                } catch (exception: ResponseException) {
                    if (exception.response.status.value == HttpStatusCode.NotFound.value) return true else throw exception
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    public suspend fun getLogs(
        offset: Int? = null,
        length: Int? = null,
        logType: LogType? = null,
        requestOptions: RequestOptions? = null
    ): ResponseLogs {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyOffset, offset)
            parameter(KeyLength, length)
            parameter(KeyType, logType?.raw)
        }

        return transport.request(HttpMethod.Get, CallType.Read, RouteLogs, options)
    }

    companion object {

        public fun generateAPIKey(parentAPIKey: APIKey, restriction: SecuredAPIKeyRestriction): APIKey {
            val restrictionString = restriction.buildRestrictionString()
            val hash = parentAPIKey.raw.sha256(restrictionString)

            return "$hash$restrictionString".encodeBase64().toAPIKey()
        }
    }
}