package com.algolia.search.model.response.deletion

import com.algolia.search.model.APIKey
import com.algolia.search.model.ClientDate

public data class DeletionAPIKey(
    /**
     * The date at which the [APIKey] was deleted.
     */
    val deletedAt: ClientDate,
    /**
     * Convenience [APIKey] that was deleted.
     */
    val apiKey: APIKey
)
