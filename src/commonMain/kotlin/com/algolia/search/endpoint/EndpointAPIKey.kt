package com.algolia.search.endpoint

import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.apikey.ResponseAPIKey
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
    ): ResponseAPIKey.Save

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
    ): ResponseAPIKey.Save

    suspend fun deleteAPIKey(apiKey: APIKey): ResponseAPIKey.Delete

    suspend fun listAPIKeys(): ResponseAPIKey.GetList

    suspend fun getAPIKeyPermission(apiKey: APIKey): ResponseAPIKey.Get

    suspend fun addIndexAPIKey(
        indexName: IndexName,
        rights: List<ACL>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): ResponseAPIKey.Save

    suspend fun updateIndexAPIKey(
        indexName: IndexName,
        rights: List<ACL>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): ResponseAPIKey.Update

    suspend fun deleteIndexAPIKey(indexName: IndexName, apiKey: APIKey): ResponseAPIKey.Delete

    suspend fun getIndexAPIKey(indexName: IndexName, apiKey: APIKey): ResponseAPIKey.Get

    suspend fun listIndexAPIKeys(indexName: IndexName): ResponseAPIKey.GetList

    suspend fun listIndexAPIKeys(): ResponseAPIKey.GetList
}