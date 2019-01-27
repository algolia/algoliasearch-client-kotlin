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


internal class APIKeyClient(
    val client: Client
) : APIKeyEndpoint,
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
    ): APIKeyResponse.Save {
        return write.retry(writeTimeout, "/1/keys") { path ->
            httpClient.post<APIKeyResponse.Save>(path) {
                body = APIKeyRequest(
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
    ): APIKeyResponse.Save {
        return write.retry(writeTimeout, "/1/keys/$apiKey") { path ->
            httpClient.put<APIKeyResponse.Save>(path) {
                body = APIKeyRequest(
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

    override suspend fun deleteAPIKey(apiKey: APIKey): APIKeyResponse.Delete {
        return write.retry(writeTimeout, "/1/keys/$apiKey") { path ->
            httpClient.delete<APIKeyResponse.Delete>(path)
        }
    }

    override suspend fun listAPIKeys(): APIKeyResponse.GetList {
        return read.retry(readTimeout, "/1/keys") { path ->
            httpClient.get<APIKeyResponse.GetList>(path)
        }
    }

    override suspend fun getAPIKeyPermission(apiKey: APIKey): APIKeyResponse.Get {
        return read.retry(readTimeout, "/1/keys/$apiKey") { path ->
            httpClient.get<APIKeyResponse.Get>(path)
        }
    }
}