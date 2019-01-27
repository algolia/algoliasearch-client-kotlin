package com.algolia.search.apikey

import com.algolia.search.client.Client
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.encodeNoNulls
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put


internal class APIKeyClientIndex(
    val client: Client,
    override val indexName: IndexName
) : APIKeyEndpointIndex,
    Client by client {

    override suspend fun getIndexAPIKey(apiKey: APIKey): APIKeyResponse.Get {
        return read.retry(readTimeout, indexName.pathIndexes("/keys/$apiKey")) { path ->
            httpClient.get<APIKeyResponse.Get>(path)
        }
    }

    override suspend fun addIndexAPIKey(
        rights: List<ACL>?,
        description: String?,
        maxHitsPerQuery: Int?,
        maxQueriesPerIPPerHour: Int?,
        validity: Long?,
        query: Query?,
        referers: List<String>?
    ): APIKeyResponse.Save {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.post<APIKeyResponse.Save>(path) {
                body = APIKeyRequest(
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
    ): APIKeyResponse.Update {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.put<APIKeyResponse.Update>(path) {
                body = APIKeyRequest(
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

    override suspend fun deleteIndexAPIKey(apiKey: APIKey): APIKeyResponse.Delete {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys/$apiKey")) { path ->
            httpClient.delete<APIKeyResponse.Delete>(path)
        }
    }

    override suspend fun listIndexAPIKeys(): APIKeyResponse.GetList {
        return read.retry(readTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.get<APIKeyResponse.GetList>(path)
        }
    }
}