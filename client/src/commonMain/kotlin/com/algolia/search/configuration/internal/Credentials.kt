@file:Suppress("FunctionName")

package com.algolia.search.configuration.internal

import com.algolia.search.configuration.Credentials
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID

internal class CredentialsImpl(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey,
) : Credentials

/**
 * Create [Credentials] instance.
 */
internal fun Credentials(
    applicationID: ApplicationID,
    apiKey: APIKey,
): Credentials = CredentialsImpl(applicationID, apiKey)
