package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.search.Query
import com.algolia.search.response.ResponseAPIKeyPermission
import com.algolia.search.response.ResponseListAPIKey
import com.algolia.search.response.creation.CreationAPIKey
import com.algolia.search.response.deletion.Deletion


interface EndpointAPIKey {

    suspend fun addAPIKey(
        rights: List<ACL>? = null,
        indexes: List<IndexName>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null,
        requestOptions: RequestOptions? = null
    ): CreationAPIKey

    suspend fun updateAPIKey(
        apiKey: APIKey,
        rights: List<ACL>? = null,
        indexes: List<IndexName>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        query: Query? = null,
        referers: List<String>? = null,
        requestOptions: RequestOptions? = null
    ): CreationAPIKey

    suspend fun deleteAPIKey(apiKey: APIKey, requestOptions: RequestOptions? = null): Deletion

    suspend fun getAPIKeyPermission(apiKey: APIKey, requestOptions: RequestOptions? = null): ResponseAPIKeyPermission

    suspend fun listAPIKeys(requestOptions: RequestOptions? = null): ResponseListAPIKey
}