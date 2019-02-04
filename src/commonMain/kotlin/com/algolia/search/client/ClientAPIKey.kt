package com.algolia.search.client

import com.algolia.search.endpoint.EndpointAPIKey
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.search.Query
import com.algolia.search.request.RequestAPIKey
import com.algolia.search.response.ResponseAPIKeyPermission
import com.algolia.search.response.ResponseListAPIKey
import com.algolia.search.response.creation.CreationAPIKey
import com.algolia.search.response.deletion.Deletion
import com.algolia.search.serialize.stringify
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
    ): CreationAPIKey {
        return write.retry(writeTimeout, "/1/keys") { path ->
            httpClient.post<CreationAPIKey>(path) {
                body = RequestAPIKey(
                    rights = rights,
                    indexes = indexes,
                    description = description,
                    maxHitsPerQuery = maxHitsPerQuery,
                    maxQueriesPerIPPerHour = maxQueriesPerIPPerHour,
                    validity = validity,
                    query = query,
                    referers = referers
                ).stringify()
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
    ): CreationAPIKey {
        return write.retry(writeTimeout, "/1/keys/$apiKey") { path ->
            httpClient.put<CreationAPIKey>(path) {
                body = RequestAPIKey(
                    rights = rights,
                    indexes = indexes,
                    description = description,
                    maxHitsPerQuery = maxHitsPerQuery,
                    maxQueriesPerIPPerHour = maxQueriesPerIPPerHour,
                    validity = validity,
                    query = query,
                    referers = referers
                ).stringify()
            }
        }
    }

    override suspend fun deleteAPIKey(apiKey: APIKey): Deletion {
        return write.retry(writeTimeout, "/1/keys/$apiKey") { path ->
            httpClient.delete<Deletion>(path)
        }
    }

    override suspend fun listAPIKeys(): ResponseListAPIKey {
        return read.retry(readTimeout, "/1/keys") { path ->
            httpClient.get<ResponseListAPIKey>(path)
        }
    }

    override suspend fun getAPIKeyPermission(apiKey: APIKey): ResponseAPIKeyPermission {
        return read.retry(readTimeout, "/1/keys/$apiKey") { path ->
            httpClient.get<ResponseAPIKeyPermission>(path)
        }
    }
}