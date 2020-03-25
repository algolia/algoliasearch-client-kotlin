package com.algolia.search.configuration

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID

internal data class CredentialsImpl(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey
) : Credentials
