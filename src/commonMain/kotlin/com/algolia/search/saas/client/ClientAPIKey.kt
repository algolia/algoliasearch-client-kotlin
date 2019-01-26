package com.algolia.search.saas.client

import com.algolia.search.saas.endpoint.EndpointAPIKey
import com.algolia.search.saas.model.APIKey
import com.algolia.search.saas.model.IndexName
import com.algolia.search.saas.model.apiKey.*
import com.algolia.search.saas.model.indexing.TaskUpdateObject
import com.algolia.search.saas.model.search.Query
import com.algolia.search.saas.serialize.encodeNoNulls
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put


internal class ClientAPIKey(
    val client: Client
) : EndpointAPIKey,
    Client by client {

    override suspend fun addAPIKey(
        rights: List<ACL>?,
        indexes: List<IndexName>?,
        description: String?,
        maxHitsPerQuery: Int?,
        maxQueriesPerIPPerHour: Int?,
        validity: Long?,
        query: Query?,
        referers: List<String>?
    ): SaveAPIKeyResponse {
        return write.retry(writeTimeout, "/1/keys") { path ->
            httpClient.post<SaveAPIKeyResponse>(path) {
                body = APIKeyCreateBody(
                    rights = rights,
                    indexes = indexes,
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

    override suspend fun updateAPIKey(
        apiKey: APIKey,
        rights: List<ACL>?,
        indexes: List<IndexName>?,
        description: String?,
        maxHitsPerQuery: Int?,
        maxQueriesPerIPPerHour: Int?,
        validity: Long?,
        query: Query?,
        referers: List<String>?
    ): SaveAPIKeyResponse {
        return write.retry(writeTimeout, "/1/keys/$apiKey") { path ->
            httpClient.put<SaveAPIKeyResponse>(path) {
                body = APIKeyCreateBody(
                    rights = rights,
                    indexes = indexes,
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

    override suspend fun deleteAPIKey(apiKey: APIKey): DeleteAPIKeyResponse {
        return write.retry(writeTimeout, "/1/keys/$apiKey") { path ->
            httpClient.delete<DeleteAPIKeyResponse>(path)
        }
    }

    override suspend fun listAPIKeys(): ListAPiKeysResponse {
        return read.retry(readTimeout, "/1/keys") { path ->
            httpClient.get<ListAPiKeysResponse>(path)
        }
    }

    override suspend fun getAPIKeyPermission(apiKey: APIKey): APIKeyResponse {
        return read.retry(readTimeout, "/1/keys/$apiKey") { path ->
            httpClient.get<APIKeyResponse>(path)
        }
    }

    override suspend fun getIndexAPIKey(indexName: IndexName, apiKey: APIKey): APIKeyResponse {
        return read.retry(readTimeout, indexName.pathIndexes("/keys/$apiKey")) { path ->
            httpClient.get<APIKeyResponse>(path)
        }
    }

    override suspend fun addIndexAPIKey(
        indexName: IndexName,
        rights: List<ACL>?,
        description: String?,
        maxHitsPerQuery: Int?,
        maxQueriesPerIPPerHour: Int?,
        validity: Long?,
        query: Query?,
        referers: List<String>?
    ): SaveAPIKeyResponse {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.post<SaveAPIKeyResponse>(path) {
                body = APIKeyCreateBody(
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
        indexName: IndexName,
        rights: List<ACL>?,
        description: String?,
        maxHitsPerQuery: Int?,
        maxQueriesPerIPPerHour: Int?,
        validity: Long?,
        query: Query?,
        referers: List<String>?
    ): TaskUpdateObject {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.put<TaskUpdateObject>(path) {
                body = APIKeyCreateBody(
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

    override suspend fun deleteIndexAPIKey(indexName: IndexName, apiKey: APIKey): DeleteAPIKeyResponse {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys/$apiKey")) { path ->
            httpClient.delete<DeleteAPIKeyResponse>(path)
        }
    }

    override suspend fun listIndexAPIKeys(indexName: IndexName): ListAPiKeysResponse {
        return read.retry(readTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.get<ListAPiKeysResponse>(path)
        }
    }

    override suspend fun listIndexAPIKeys(): ListAPiKeysResponse {
        return read.retry(readTimeout, "/1/indexes/*/keys") { path ->
            httpClient.get<ListAPiKeysResponse>(path)
        }
    }
}