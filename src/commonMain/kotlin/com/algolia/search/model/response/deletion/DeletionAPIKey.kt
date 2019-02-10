package com.algolia.search.model.response.deletion

import com.algolia.search.model.APIKey
import com.algolia.search.model.Datable


data class DeletionAPIKey(
    override val date: String,
    val apiKey: APIKey
) : Datable