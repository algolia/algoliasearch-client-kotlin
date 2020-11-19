@file:Suppress("FunctionName")

package com.algolia.search.client

import com.algolia.search.client.internal.ClientAnswersImpl
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationAnswers
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.internal.DEFAULT_LOG_LEVEL
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.transport.internal.Transport
import io.ktor.client.features.logging.LogLevel

/**
 * Client for the Answers API.
 */
public interface ClientAnswers : Configuration, Credentials {

    /**
     * Initialize an [AnswersIndex] configured with [ConfigurationAnswers].
     */
    public fun initIndex(indexName: IndexName): AnswersIndex

    public companion object
}

/**
 * Create a [ClientAnswers] instance.
 *
 * @param applicationID application ID
 * @param apiKey API Key
 * @param logLevel logging level
 */
public fun ClientAnswers(
    applicationID: ApplicationID,
    apiKey: APIKey,
    logLevel: LogLevel = DEFAULT_LOG_LEVEL,
): ClientAnswers = ClientAnswers(
    ConfigurationAnswers(applicationID = applicationID, apiKey = apiKey, logLevel = logLevel)
)

/**
 * Create a [ClientAnswers] instance.
 *
 * @param configuration answers configuration
 */
public fun ClientAnswers(
    configuration: ConfigurationAnswers,
): ClientAnswers = ClientAnswersImpl(
    Transport(configuration, configuration)
)
