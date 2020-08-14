@file:Suppress("FunctionName")

package com.algolia.search.client

import com.algolia.search.client.internal.ClientSearchImpl
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.internal.DEFAULT_LOG_LEVEL
import com.algolia.search.configuration.internal.Credentials
import com.algolia.search.endpoint.EndpointAPIKey
import com.algolia.search.endpoint.EndpointMultiCluster
import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.helper.internal.decodeBase64
import com.algolia.search.helper.internal.encodeBase64
import com.algolia.search.helper.internal.sha256
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
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.client.features.logging.LogLevel

/**
 * Client to perform operations on indices.
 */
public interface ClientSearch : EndpointMultipleIndex, EndpointAPIKey, EndpointMultiCluster,
    Configuration, Credentials {

    /**
     *  Initialize an [Index] configured with [ConfigurationSearch].
     */
    public fun initIndex(indexName: IndexName): Index

    /**
     * Wait on multiple [TaskIndex] operations. To be used with [multipleBatchObjects].
     *
     * @param timeout If a value is specified, the method will throw [TimeoutCancellationException] after waiting for
     * the allotted time in milliseconds.
     */
    public suspend fun List<TaskIndex>.waitAll(timeout: Long? = null): List<TaskStatus>

    /**
     * Convenience method similar to [waitAll] but with a [ResponseBatches] as receiver.
     */
    public suspend fun ResponseBatches.waitAll(timeout: Long? = null): List<TaskStatus>

    /**
     * Wait on a [CreationAPIKey] operation.
     *
     * @param timeout If a value is specified, the method will throw [TimeoutCancellationException] after waiting for
     * the allotted time in milliseconds.
     */
    public suspend fun CreationAPIKey.wait(timeout: Long? = null): ResponseAPIKey

    /**
     * Wait on a [DeletionAPIKey] operation.
     *
     * @param timeout If a value is specified, the method will throw [TimeoutCancellationException] after waiting for
     * the allotted time in milliseconds.
     */
    public suspend fun DeletionAPIKey.wait(timeout: Long? = null): Boolean

    /**
     * Convenience methods to get the logs directly from the [ClientSearch] without instantiating an [Index].
     *
     * @see EndpointAdvanced.getLogs
     */
    public suspend fun getLogs(
        page: Int? = null,
        hitsPerPage: Int? = null,
        logType: LogType? = null,
        requestOptions: RequestOptions? = null,
    ): ResponseLogs

    public companion object {
        /**
         * Generate a virtual [APIKey] without any call to the server.
         * Use [SecuredAPIKeyRestriction] to restrict [APIKey] usage.
         * You can generate a Secured APIKey from any [APIKey].
         * Learn more about secured [API keys][https://www.algolia.com/doc/guides/security/api-keys/#secured-api-keys].
         */
        public fun generateAPIKey(parentAPIKey: APIKey, restriction: SecuredAPIKeyRestriction): APIKey {
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
        public fun getSecuredApiKeyRemainingValidity(apiKey: APIKey): Long {
            val decoded = apiKey.raw.decodeBase64()
            val pattern = Regex("validUntil=(\\d+)")
            val match = pattern.find(decoded)
            return if (match != null) {
                match.groupValues[1].toLong() - Time.getCurrentTimeMillis()
            } else {
                throw IllegalArgumentException("The Secured API Key doesn't have a validUntil parameter.")
            }
        }
    }
}

/**
 * Create a [ClientSearch] instance.
 *
 * @param applicationID application ID
 * @param apiKey API Key
 * @param logLevel log level
 */
public fun ClientSearch(
    applicationID: ApplicationID,
    apiKey: APIKey,
    logLevel: LogLevel = DEFAULT_LOG_LEVEL,
): ClientSearch = ClientSearchImpl(
    Transport(
        ConfigurationSearch(applicationID = applicationID, apiKey = apiKey, logLevel = logLevel),
        Credentials(applicationID = applicationID, apiKey = apiKey)
    )
)

/**
 * Create a [ClientSearch] instance.
 *
 * @param configuration search configuration
 */
public fun ClientSearch(
    configuration: ConfigurationSearch,
): ClientSearch = ClientSearchImpl(
    Transport(configuration, configuration)
)
