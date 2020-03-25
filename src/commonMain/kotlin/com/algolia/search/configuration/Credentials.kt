package com.algolia.search.configuration

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID

/**
 * Configuration used by a client for authenticated request.
 */
interface Credentials {

    /**
     * [ApplicationID] to target. Is passed as a HTTP header.
     */
    val applicationID: ApplicationID

    /**
     * [APIKey] for a given [ApplicationID]. Is passed as a HTTP header.
     * To maintain security, never use your Admin [APIKey] on your front end or share it with anyone.
     * In your front end, use the Search-only [APIKey] or any other key that has search-only rights.
     */
    val apiKey: APIKey
}
