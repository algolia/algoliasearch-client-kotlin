package com.algolia.search.apikey

import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query


interface APIKeyEndpoint {

    suspend fun addAPIKey(
        rights: List<ACL>? = null,
        indexes: List<IndexName>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): APIKeyResponse.Save

    suspend fun updateAPIKey(
        apiKey: APIKey,
        rights: List<ACL>? = null,
        indexes: List<IndexName>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): APIKeyResponse.Save

    suspend fun deleteAPIKey(apiKey: APIKey): APIKeyResponse.Delete

    suspend fun getAPIKeyPermission(apiKey: APIKey): APIKeyResponse.Get

    suspend fun listAPIKeys(): APIKeyResponse.GetList
}