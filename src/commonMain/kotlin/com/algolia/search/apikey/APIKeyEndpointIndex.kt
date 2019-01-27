package com.algolia.search.apikey

import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query


interface APIKeyEndpointIndex {

    val indexName: IndexName

    suspend fun addIndexAPIKey(
        rights: List<ACL>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): APIKeyResponse.Save

    suspend fun updateIndexAPIKey(
        rights: List<ACL>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): APIKeyResponse.Update

    suspend fun deleteIndexAPIKey(apiKey: APIKey): APIKeyResponse.Delete

    suspend fun getIndexAPIKey(apiKey: APIKey): APIKeyResponse.Get

    suspend fun listIndexAPIKeys(): APIKeyResponse.GetList
}