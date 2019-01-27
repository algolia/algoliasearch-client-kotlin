package com.algolia.search.client

import com.algolia.search.model.ACL
import com.algolia.search.request.RequestAPIKey
import com.algolia.search.endpoint.EndpointAPIKeyIndex
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.response.ResponseAPIKeyPermission
import com.algolia.search.response.ResponseListAPIKey
import com.algolia.search.response.creation.CreationAPIKey
import com.algolia.search.response.deletion.Deletion
import com.algolia.search.serialize.encodeNoNulls
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put


internal class ClientAPIKeyIndex(
    val client: Client,
    override val indexName: IndexName
) : EndpointAPIKeyIndex,
    Client by client {

    override suspend fun addIndexAPIKey(
        rights: List<ACL>?,
        description: String?,
        maxHitsPerQuery: Int?,
        maxQueriesPerIPPerHour: Int?,
        validity: Long?,
        query: Query?,
        referers: List<String>?
    ): CreationAPIKey {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.post<CreationAPIKey>(path) {
                body = RequestAPIKey(
                    rights = rights,
                    description = description,
                    maxHitsPerQuery = maxHitsPerQuery,
                    maxQueriesPerIPPerHour = maxQueriesPerIPPerHour,
                    validity = validity,
                    query = query,
                    referers = referers
                ).encodeNoNulls().toString()
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
        referers: List<String>?
    ): CreationAPIKey {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.put<CreationAPIKey>(path) {
                body = RequestAPIKey(
                    rights = rights,
                    description = description,
                    maxHitsPerQuery = maxHitsPerQuery,
                    maxQueriesPerIPPerHour = maxQueriesPerIPPerHour,
                    validity = validity,
                    query = query,
                    referers = referers
                ).encodeNoNulls().toString()
            }
        }
    }

    override suspend fun deleteIndexAPIKey(apiKey: APIKey): Deletion {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys/$apiKey")) { path ->
            httpClient.delete<Deletion>(path)
        }
    }

    override suspend fun getIndexAPIKey(apiKey: APIKey): ResponseAPIKeyPermission {
        return read.retry(readTimeout, indexName.pathIndexes("/keys/$apiKey")) { path ->
            httpClient.get<ResponseAPIKeyPermission>(path)
        }
    }

    override suspend fun listIndexAPIKeys(): ResponseListAPIKey {
        return read.retry(readTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.get<ResponseListAPIKey>(path)
        }
    }
}