package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.search.Query
import com.algolia.search.model.response.ResponseAPIKeyPermission
import com.algolia.search.model.response.ResponseListAPIKey
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.Deletion
import com.algolia.search.model.response.revision.RevisionAPIKey


interface EndpointAPIKeyIndex {

    val indexName: IndexName

    suspend fun addIndexAPIKey(
        rights: List<ACL>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null,
        requestOptions: RequestOptions? = null
    ): CreationAPIKey

    suspend fun updateIndexAPIKey(
        rights: List<ACL>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null,
        requestOptions: RequestOptions? = null
    ): RevisionAPIKey

    suspend fun deleteIndexAPIKey(apiKey: APIKey, requestOptions: RequestOptions? = null): Deletion

    suspend fun getIndexAPIKey(apiKey: APIKey, requestOptions: RequestOptions? = null): ResponseAPIKeyPermission

    suspend fun listIndexAPIKeys(requestOptions: RequestOptions? = null): ResponseListAPIKey
}