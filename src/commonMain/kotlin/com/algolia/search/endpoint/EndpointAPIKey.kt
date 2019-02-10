package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.response.ResponseAPIKey
import com.algolia.search.model.response.ResponseListAPIKey
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.DeletionAPIKey
import com.algolia.search.model.response.revision.RevisionAPIKey
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
        referers: List<String>? = null,
        restrictSources: String? = null,
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
    ): RevisionAPIKey

    suspend fun deleteAPIKey(apiKey: APIKey, requestOptions: RequestOptions? = null): DeletionAPIKey

    suspend fun getAPIKey(apiKey: APIKey, requestOptions: RequestOptions? = null): ResponseAPIKey

    suspend fun listAPIKeys(requestOptions: RequestOptions? = null): ResponseListAPIKey
}