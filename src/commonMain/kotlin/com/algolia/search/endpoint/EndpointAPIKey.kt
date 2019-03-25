package com.algolia.search.endpoint

import com.algolia.search.model.APIKey
import com.algolia.search.model.apikey.APIKeyParams
import com.algolia.search.model.response.ResponseAPIKey
import com.algolia.search.model.response.ResponseListAPIKey
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.DeletionAPIKey
import com.algolia.search.model.response.revision.RevisionAPIKey
import com.algolia.search.transport.RequestOptions


public interface EndpointAPIKey {

    suspend fun addAPIKey(
        params: APIKeyParams,
        restrictSources: String? = null,
        requestOptions: RequestOptions? = null
    ): CreationAPIKey

    suspend fun updateAPIKey(
        apiKey: APIKey,
        params: APIKeyParams,
        requestOptions: RequestOptions? = null
    ): RevisionAPIKey

    suspend fun deleteAPIKey(
        apiKey: APIKey,
        requestOptions: RequestOptions? = null
    ): DeletionAPIKey

    suspend fun restoreAPIKey(
        apiKey: APIKey,
        requestOptions: RequestOptions? = null
    ): CreationAPIKey

    suspend fun getAPIKey(
        apiKey: APIKey,
        requestOptions: RequestOptions? = null
    ): ResponseAPIKey

    suspend fun listAPIKeys(
        requestOptions: RequestOptions? = null
    ): ResponseListAPIKey
}