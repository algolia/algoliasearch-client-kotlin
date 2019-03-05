package com.algolia.search.model.response.deletion

import com.algolia.search.model.APIKey
import com.algolia.search.model.ClientDate


public data class DeletionAPIKey(
    val deletedAt: ClientDate,
    val apiKey: APIKey
)