package com.algolia.search.client

import com.algolia.search.endpoint.EndpointAPIKey
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.apikey.RequestAPIKey
import com.algolia.search.model.apikey.ResponseAPIKey
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.encodeNoNulls
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
    ): ResponseAPIKey.Save {
        return write.retry(writeTimeout, "/1/keys") { path ->
            httpClient.post<ResponseAPIKey.Save>(path) {
                body = RequestAPIKey(
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
    ): ResponseAPIKey.Save {
        return write.retry(writeTimeout, "/1/keys/$apiKey") { path ->
            httpClient.put<ResponseAPIKey.Save>(path) {
                body = RequestAPIKey(
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

    override suspend fun deleteAPIKey(apiKey: APIKey): ResponseAPIKey.Delete {
        return write.retry(writeTimeout, "/1/keys/$apiKey") { path ->
            httpClient.delete<ResponseAPIKey.Delete>(path)
        }
    }

    override suspend fun listAPIKeys(): ResponseAPIKey.GetList {
        return read.retry(readTimeout, "/1/keys") { path ->
            httpClient.get<ResponseAPIKey.GetList>(path)
        }
    }

    override suspend fun getAPIKeyPermission(apiKey: APIKey): ResponseAPIKey.Get {
        return read.retry(readTimeout, "/1/keys/$apiKey") { path ->
            httpClient.get<ResponseAPIKey.Get>(path)
        }
    }

    override suspend fun getIndexAPIKey(indexName: IndexName, apiKey: APIKey): ResponseAPIKey.Get {
        return read.retry(readTimeout, indexName.pathIndexes("/keys/$apiKey")) { path ->
            httpClient.get<ResponseAPIKey.Get>(path)
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
    ): ResponseAPIKey.Save {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.post<ResponseAPIKey.Save>(path) {
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
        indexName: IndexName,
        rights: List<ACL>?,
        description: String?,
        maxHitsPerQuery: Int?,
        maxQueriesPerIPPerHour: Int?,
        validity: Long?,
        query: Query?,
        referers: List<String>?
    ): ResponseAPIKey.Update {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.put<ResponseAPIKey.Update>(path) {
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

    override suspend fun deleteIndexAPIKey(indexName: IndexName, apiKey: APIKey): ResponseAPIKey.Delete {
        return write.retry(writeTimeout, indexName.pathIndexes("/keys/$apiKey")) { path ->
            httpClient.delete<ResponseAPIKey.Delete>(path)
        }
    }

    override suspend fun listIndexAPIKeys(indexName: IndexName): ResponseAPIKey.GetList {
        return read.retry(readTimeout, indexName.pathIndexes("/keys")) { path ->
            httpClient.get<ResponseAPIKey.GetList>(path)
        }
    }

    override suspend fun listIndexAPIKeys(): ResponseAPIKey.GetList {
        return read.retry(readTimeout, "/1/indexes/*/keys") { path ->
            httpClient.get<ResponseAPIKey.GetList>(path)
        }
    }
}