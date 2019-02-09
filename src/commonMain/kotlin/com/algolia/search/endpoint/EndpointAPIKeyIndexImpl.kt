package com.algolia.search.endpoint

import com.algolia.search.client.APIWrapper
import com.algolia.search.client.RequestOptions
import com.algolia.search.client.setRequestOptions
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.search.Query
import com.algolia.search.model.request.RequestAPIKey
import com.algolia.search.model.response.ResponseAPIKeyPermission
import com.algolia.search.model.response.ResponseListAPIKey
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.Deletion
import com.algolia.search.model.response.revision.RevisionAPIKey
import com.algolia.search.serialize.stringify
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put


internal class EndpointAPIKeyIndexImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointAPIKeyIndex,
    APIWrapper by api {

    private val route = "/keys"

    override suspend fun addIndexAPIKey(
        rights: List<ACL>?,
        description: String?,
        maxHitsPerQuery: Int?,
        maxQueriesPerIPPerHour: Int?,
        validity: Long?,
        query: Query?,
        referers: List<String>?,
        requestOptions: RequestOptions?
    ): CreationAPIKey {
        val bodyString = RequestAPIKey(
            rights = rights,
            description = description,
            maxHitsPerQuery = maxHitsPerQuery,
            maxQueriesPerIPPerHour = maxQueriesPerIPPerHour,
            validity = validity,
            query = query,
            referers = referers
        ).stringify()

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes(route)) { path ->
            httpClient.post<CreationAPIKey>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun updateIndexAPIKey(
        rights: List<ACL>?,
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
            description = description,
            maxHitsPerQuery = maxHitsPerQuery,
            maxQueriesPerIPPerHour = maxQueriesPerIPPerHour,
            validity = validity,
            query = query,
            referers = referers
        ).stringify()

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes(route)) { path ->
            httpClient.put<RevisionAPIKey>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteIndexAPIKey(apiKey: APIKey, requestOptions: RequestOptions?): Deletion {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("$route/$apiKey")) { path ->
            httpClient.delete<Deletion>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getIndexAPIKey(apiKey: APIKey, requestOptions: RequestOptions?): ResponseAPIKeyPermission {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("$route/$apiKey")) { path ->
            httpClient.get<ResponseAPIKeyPermission>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun listIndexAPIKeys(requestOptions: RequestOptions?): ResponseListAPIKey {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes(route)) { path ->
            httpClient.get<ResponseListAPIKey>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }
}