package com.algolia.search.endpoint

import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.response.ResponseAPIKeyPermission
import com.algolia.search.response.ResponseListAPIKey
import com.algolia.search.response.creation.CreationAPIKey
import com.algolia.search.response.deletion.Deletion


interface EndpointAPIKeyIndex {

    val indexName: IndexName

    suspend fun addIndexAPIKey(
        rights: List<ACL>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): CreationAPIKey

    suspend fun updateIndexAPIKey(
        rights: List<ACL>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null
    ): CreationAPIKey

    suspend fun deleteIndexAPIKey(apiKey: APIKey): Deletion

    suspend fun getIndexAPIKey(apiKey: APIKey): ResponseAPIKeyPermission

    suspend fun listIndexAPIKeys(): ResponseListAPIKey
}