package com.algolia.search.client

import com.algolia.search.endpoint.*
import com.algolia.search.helper.encodeBase64
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
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.BadResponseStatusException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout


public class ClientSearch private constructor(
    internal val api: APIWrapperImpl
) :
    EndpointMultipleIndex by EndpointMultipleIndexImpl(api),
    EndpointAPIKey by EndpointAPIKeyImpl(api),
    EndpointMultiCluster by EndpointMulticlusterImpl(api),
    Configuration by api {

    public constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(APIWrapperImpl(ConfigurationImpl(applicationID, apiKey, hosts = null)))

    public constructor(
        configuration: ConfigurationImpl
    ) : this(APIWrapperImpl(configuration))

    public constructor(
        configuration: ConfigurationImpl,
        engine: HttpClientEngine?
    ) : this(APIWrapperImpl(configuration, engine))

    public fun initIndex(indexName: IndexName): Index {
        return Index(api, indexName)
    }

    // Todo test this
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

    // TODO Specify why there is no taskID in a comment
    public suspend fun CreationAPIKey.wait(timeout: Long? = null): ResponseAPIKey {

        suspend fun loop(): ResponseAPIKey {
            while (true) {
                try {
                    return getAPIKey(apiKey)
                } catch (exception: BadResponseStatusException) {
                    if (exception.statusCode.value != HttpStatusCode.NotFound.value) throw exception
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    // TODO Specify why there is no taskID in a comment
    public suspend fun DeletionAPIKey.wait(timeout: Long? = null): Boolean {

        suspend fun loop(): Boolean {
            while (true) {
                try {
                    getAPIKey(apiKey)
                } catch (exception: BadResponseStatusException) {
                    if (exception.statusCode.value == HttpStatusCode.NotFound.value) return true else throw exception
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
        return api.run {
            retryRead(requestOptions, "/1/logs") { url ->
                httpClient.get<ResponseLogs>(url) {
                    parameter(KeyOffset, offset)
                    parameter(KeyLength, length)
                    parameter(KeyType, logType?.raw)
                    setRequestOptions(requestOptions)
                }
            }
        }
    }

    companion object {

        public fun generateAPIKey(parentAPIKey: APIKey, restriction: SecuredAPIKeyRestriction): APIKey {
            val restrictionString = restriction.buildRestrictionString()
            val hash = parentAPIKey.raw.sha256(restrictionString)

            return "$hash$restrictionString".encodeBase64().toAPIKey()
        }
    }
}