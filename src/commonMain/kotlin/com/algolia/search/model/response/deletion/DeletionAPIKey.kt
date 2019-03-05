package com.algolia.search.model.response.deletion

import com.algolia.search.ClientDate
import com.algolia.search.model.APIKey


data class DeletionAPIKey(
    val deletedAt: ClientDate,
    val apiKey: APIKey
)