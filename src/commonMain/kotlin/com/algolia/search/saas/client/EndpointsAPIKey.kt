package com.algolia.search.saas.client

import com.algolia.search.saas.data.*


interface EndpointsAPIKey {

    suspend fun addAPIKey(
        rights: List<ACL>? = null,
        indexes: List<IndexName>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): CreateAPIKey

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
    ): CreateAPIKey

    suspend fun deleteAPIKey(apiKey: APIKey): DeleteAPIKey

    suspend fun listAPIKeys(): List<GetAPIKey>

    suspend fun getAPIKeyPermission(apiKey: APIKey): GetAPIKey

    suspend fun addIndexAPIKey(
        indexName: IndexName,
        rights: List<ACL>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): CreateAPIKey

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

    suspend fun deleteIndexAPIKey(indexName: IndexName, apiKey: APIKey): DeleteAPIKey

    suspend fun getIndexAPIKey(indexName: IndexName, apiKey: APIKey): GetAPIKey

    suspend fun listIndexAPIKeys(indexName: IndexName): List<GetAPIKey>

    suspend fun listIndexAPIKeys(): List<GetAPIKey>
}