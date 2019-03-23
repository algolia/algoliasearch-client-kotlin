package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
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
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod


internal class EndpointAPIKeyImpl(
    private val transport: Transport
) : EndpointAPIKey {

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
        val body = RequestAPIKey(
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

        return transport.request(HttpMethod.Post, CallType.Write, RouteKeysV1, requestOptions, body)
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
        val body = RequestAPIKey(
            rights = rights,
            indices = indices,
            description = description,
            maxHitsPerQuery = maxHitsPerQuery,
            maxQueriesPerIPPerHour = maxQueriesPerIPPerHour,
            validity = validity,
            query = query?.toJsonNoDefaults()?.urlEncode(),
            referers = referers
        ).stringify()

        return transport.request(HttpMethod.Put, CallType.Write, "$RouteKeysV1/$apiKey", requestOptions, body)
    }

    override suspend fun deleteAPIKey(apiKey: APIKey, requestOptions: RequestOptions?): DeletionAPIKey {
        return DeletionAPIKey(
            transport.request<Deletion>(
                HttpMethod.Delete,
                CallType.Write,
                "$RouteKeysV1/$apiKey",
                requestOptions
            ).deletedAt, apiKey
        )
    }

    override suspend fun listAPIKeys(requestOptions: RequestOptions?): ResponseListAPIKey {
        return transport.request(HttpMethod.Get, CallType.Read, RouteKeysV1, requestOptions)
    }

    override suspend fun getAPIKey(
        apiKey: APIKey,
        requestOptions: RequestOptions?
    ): ResponseAPIKey {
        return transport.request(HttpMethod.Get, CallType.Read, "$RouteKeysV1/$apiKey", requestOptions)
    }
}