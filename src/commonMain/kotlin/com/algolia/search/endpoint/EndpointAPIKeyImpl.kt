package com.algolia.search.endpoint

import com.algolia.search.client.*
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.request.RequestAPIKey
import com.algolia.search.model.response.ResponseAPIKey
import com.algolia.search.model.response.ResponseListAPIKey
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.Deletion
import com.algolia.search.model.response.deletion.DeletionAPIKey
import com.algolia.search.model.response.revision.RevisionAPIKey
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.RouteKeysV1
import com.algolia.search.serialize.stringify
import com.algolia.search.serialize.toJsonNoDefaults
import com.algolia.search.serialize.urlEncode
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put


internal class EndpointAPIKeyImpl(
    val api: APIWrapper
) : EndpointAPIKey,
    APIWrapper by api {

    override suspend fun addAPIKey(
        rights: List<ACL>?,
        indices: List<IndexName>?,
        description: String?,
        maxHitsPerQuery: Int?,
        maxQueriesPerIPPerHour: Int?,
        validity: Long?,
        query: Query?,
        referers: List<String>?,
        restrictSources: String?,
        requestOptions: RequestOptions?
    ): CreationAPIKey {
        val bodyString = RequestAPIKey(
            rights = rights,
            indices = indices,
            description = description,
            maxHitsPerQuery = maxHitsPerQuery,
            maxQueriesPerIPPerHour = maxQueriesPerIPPerHour,
            validity = validity,
            query = query?.toJsonNoDefaults()?.urlEncode(),
            referers = referers,
            restrictSources = restrictSources
        ).stringify()

        return retryWrite(requestOptions, RouteKeysV1) { url ->
            httpClient.post<CreationAPIKey>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun updateAPIKey(
        apiKey: APIKey,
        rights: List<ACL>?,
        indices: List<IndexName>?,
        description: String?,
        maxHitsPerQuery: Int?,
        maxQueriesPerIPPerHour: Int?,
        validity: Long?,
        query: Query?,
        referers: List<String>?,
        requestOptions: RequestOptions?
    ): RevisionAPIKey {
        val bodyString = RequestAPIKey(
            rights = rights,
            indices = indices,
            description = description,
            maxHitsPerQuery = maxHitsPerQuery,
            maxQueriesPerIPPerHour = maxQueriesPerIPPerHour,
            validity = validity,
            query = query?.toJsonNoDefaults()?.urlEncode(),
            referers = referers
        ).stringify()

        return retryWrite(requestOptions, "$RouteKeysV1/$apiKey") { url ->
            httpClient.put<RevisionAPIKey>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteAPIKey(apiKey: APIKey, requestOptions: RequestOptions?): DeletionAPIKey {
        return retryWrite(requestOptions, "$RouteKeysV1/$apiKey") { url ->
            httpClient.delete<Deletion>(url) {
                setRequestOptions(requestOptions)
            }.let { DeletionAPIKey(it.deletedAt, apiKey) }
        }
    }

    override suspend fun listAPIKeys(requestOptions: RequestOptions?): ResponseListAPIKey {
        return retryRead(requestOptions, RouteKeysV1) { url ->
            httpClient.get<ResponseListAPIKey>(url) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getAPIKey(
        apiKey: APIKey,
        requestOptions: RequestOptions?
    ): ResponseAPIKey {
        return retryRead(requestOptions, "$RouteKeysV1/$apiKey") { url ->
            httpClient.get<ResponseAPIKey>(url) {
                setRequestOptions(requestOptions)
            }
        }
    }
}