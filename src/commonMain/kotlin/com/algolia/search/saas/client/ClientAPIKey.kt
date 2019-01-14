package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.serialize.encodeNoNulls
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put


internal class ClientAPIKey(
    val client: Client
) : EndpointsAPIKey,
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
    ): CreateAPIKey {
        return write.retry(writeTimeout, "/1/keys") { path ->
            httpClient.post<CreateAPIKey>(path) {
                body = APIKeyCreate(
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
    ): CreateAPIKey {
        return write.retry(writeTimeout, "/1/keys/$apiKey") { path ->
            httpClient.put<CreateAPIKey>(path) {
                body = APIKeyCreate(
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

    override suspend fun deleteAPIKey(apiKey: APIKey): DeleteAPIKey {
        return write.retry(writeTimeout, "/1/keys/$apiKey") { path ->
            httpClient.delete<DeleteAPIKey>(path)
        }
    }

    override suspend fun listAPIKeys(): List<GetAPIKey> {
        return read.retry(readTimeout, "/1/keys") { path ->
            httpClient.get<GetAPIKeys>(path).keys
        }
    }

    override suspend fun getAPIKeyPermission(apiKey: APIKey): GetAPIKey {
        return read.retry(readTimeout, "/1/keys/$apiKey") { path ->
            httpClient.get<GetAPIKey>(path)
        }
    }

    override suspend fun getIndexAPIKey(indexName: IndexName, apiKey: APIKey): GetAPIKey {
        return read.retry(readTimeout, indexName.pathIndexes("/keys/$apiKey")) { path ->
            httpClient.get<GetAPIKey>(path)
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
    ): CreateAPIKey {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.post<CreateAPIKey>(path) {
                body = APIKeyCreate(
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
                body = APIKeyCreate(
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

    override suspend fun deleteIndexAPIKey(indexName: IndexName, apiKey: APIKey): DeleteAPIKey {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys/$apiKey")) { path ->
            httpClient.delete<DeleteAPIKey>(path)
        }
    }

    override suspend fun listIndexAPIKeys(indexName: IndexName): List<GetAPIKey> {
        return read.retry(readTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.get<GetAPIKeys>(path).keys
        }
    }

    override suspend fun listIndexAPIKeys(): List<GetAPIKey> {
        return read.retry(readTimeout, "/1/indexes/*/keys") { path ->
            httpClient.get<GetAPIKeys>(path).keys
        }
    }
}