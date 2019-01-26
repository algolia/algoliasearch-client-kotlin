package com.algolia.search.endpoint

import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apiKey.*
import com.algolia.search.model.indexing.TaskUpdateObject
import com.algolia.search.model.search.Query


interface EndpointAPIKey {

    suspend fun addAPIKey(
        rights: List<ACL>? = null,
        indexes: List<IndexName>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): SaveAPIKeyResponse

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
    ): SaveAPIKeyResponse

    suspend fun deleteAPIKey(apiKey: APIKey): DeleteAPIKeyResponse

    suspend fun listAPIKeys(): ListAPiKeysResponse

    suspend fun getAPIKeyPermission(apiKey: APIKey): APIKeyResponse

    suspend fun addIndexAPIKey(
        indexName: IndexName,
        rights: List<ACL>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): SaveAPIKeyResponse

    suspend fun updateIndexAPIKey(
        indexName: IndexName,
        rights: List<ACL>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): TaskUpdateObject

    suspend fun deleteIndexAPIKey(indexName: IndexName, apiKey: APIKey): DeleteAPIKeyResponse

    suspend fun getIndexAPIKey(indexName: IndexName, apiKey: APIKey): APIKeyResponse

    suspend fun listIndexAPIKeys(indexName: IndexName): ListAPiKeysResponse

    suspend fun listIndexAPIKeys(): ListAPiKeysResponse
}