package com.algolia.search.client

import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.CredentialsImpl
import com.algolia.search.configuration.defaultLogLevel
import com.algolia.search.dsl.requestOptionsBuilder
import com.algolia.search.endpoint.EndpointAPIKey
import com.algolia.search.endpoint.EndpointAPIKeyImpl
import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.endpoint.EndpointMultiCluster
import com.algolia.search.endpoint.EndpointMulticlusterImpl
import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.endpoint.EndpointMultipleIndexImpl
import com.algolia.search.endpoint.EndpointPersonalization
import com.algolia.search.endpoint.EndpointPersonalizationImpl
import com.algolia.search.helper.decodeBase64
import com.algolia.search.helper.encodeBase64
import com.algolia.search.helper.sha256
import com.algolia.search.helper.toAPIKey
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.LogType
import com.algolia.search.model.Time
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
import io.ktor.client.features.logging.LogLevel
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

/**
 * Client to perform operations on indices.
 */
class ClientSearch private constructor(
    internal val transport: Transport
) :
    EndpointMultipleIndex by EndpointMultipleIndexImpl(transport),
    EndpointAPIKey by EndpointAPIKeyImpl(transport),
    EndpointMultiCluster by EndpointMulticlusterImpl(transport),
    EndpointPersonalization by EndpointPersonalizationImpl(transport),
    Configuration by transport,
    Credentials by transport.credentials {

    constructor(
        applicationID: ApplicationID,
        apiKey: APIKey,
        logLevel: LogLevel = defaultLogLevel
    ) : this(
        Transport(
            ConfigurationSearch(applicationID, apiKey, logLevel = logLevel),
            CredentialsImpl(applicationID, apiKey)
        )
    )

    constructor(
        configuration: ConfigurationSearch
    ) : this(Transport(configuration, configuration))

    /**
     *  Initialize an [Index] configured with [ConfigurationSearch].
     */
    fun initIndex(indexName: IndexName): Index {
        return Index(transport, indexName)
    }

    /**
     * Wait on multiple [TaskIndex] operations. To be used with [multipleBatchObjects].
     *
     * @param timeout If a value is specified, the method will throw [TimeoutCancellationException] after waiting for
     * the allotted time in milliseconds.
     */
    suspend fun List<TaskIndex>.waitAll(timeout: Long? = null): List<TaskStatus> {

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

    /**
     * Convenience method similar to [waitAll] but with a [ResponseBatches] as receiver.
     */
    suspend fun ResponseBatches.waitAll(timeout: Long? = null): List<TaskStatus> {
        return tasks.waitAll(timeout)
    }

    /**
     * Wait on a [CreationAPIKey] operation.
     *
     * @param timeout If a value is specified, the method will throw [TimeoutCancellationException] after waiting for
     * the allotted time in milliseconds.
     */
    suspend fun CreationAPIKey.wait(timeout: Long? = null): ResponseAPIKey {

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

    /**
     * Wait on a [DeletionAPIKey] operation.
     *
     * @param timeout If a value is specified, the method will throw [TimeoutCancellationException] after waiting for
     * the allotted time in milliseconds.
     */
    suspend fun DeletionAPIKey.wait(timeout: Long? = null): Boolean {

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

    /**
     * Convenience methods to get the logs directly from the [ClientSearch] without instantiating an [Index].
     *
     * @see EndpointAdvanced.getLogs
     */
    suspend fun getLogs(
        page: Int? = null,
        hitsPerPage: Int? = null,
        logType: LogType? = null,
        requestOptions: RequestOptions? = null
    ): ResponseLogs {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyOffset, page)
            parameter(KeyLength, hitsPerPage)
            parameter(KeyType, logType?.raw)
        }

        return transport.request(HttpMethod.Get, CallType.Read, RouteLogs, options)
    }

    companion object {

        /**
         * Generate a virtual [APIKey] without any call to the server.
         * Use [SecuredAPIKeyRestriction] to restrict [APIKey] usage.
         * You can generate a Secured APIKey from any [APIKey].
         * Learn more about secured [API keys][https://www.algolia.com/doc/guides/security/api-keys/#secured-api-keys].
         */
        fun generateAPIKey(parentAPIKey: APIKey, restriction: SecuredAPIKeyRestriction): APIKey {
            val restrictionString = restriction.buildRestrictionString()
            val hash = parentAPIKey.raw.sha256(restrictionString)

            return "$hash$restrictionString".encodeBase64().toAPIKey()
        }

        /**
         * Gets how many milliseconds are left before the secured API key expires.
         *
         * @param apiKey The secured API Key to check.
         * @return Milliseconds left before the secured API key expires.
         * @throws IllegalArgumentException if [apiKey] doesn't have a [SecuredAPIKeyRestriction.validUntil].
         */
        fun getSecuredApiKeyRemainingValidity(apiKey: APIKey): Long {
            val decoded = apiKey.raw.decodeBase64()
            val pattern = Regex("validUntil=(\\d+)")
            val match = pattern.find(decoded)

            return if (match != null) {
                val timestamp = match.groupValues[1].toLong()

                timestamp - Time.getCurrentTimeMillis()
            } else {
                throw IllegalArgumentException("The Secured API Key doesn't have a validUntil parameter.")
            }
        }
    }
}
