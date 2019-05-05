package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
import com.algolia.search.model.APIKey
import com.algolia.search.model.apikey.APIKeyParams
import com.algolia.search.model.request.RequestAPIKey
import com.algolia.search.model.response.ResponseAPIKey
import com.algolia.search.model.response.ResponseListAPIKey
import com.algolia.search.model.response.creation.Creation
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.Deletion
import com.algolia.search.model.response.deletion.DeletionAPIKey
import com.algolia.search.model.response.revision.RevisionAPIKey
import com.algolia.search.serialize.*
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject


internal class EndpointAPIKeyImpl(
    private val transport: Transport
) : EndpointAPIKey {

    override suspend fun addAPIKey(
        params: APIKeyParams,
        restrictSources: String?,
        requestOptions: RequestOptions?
    ): CreationAPIKey {
        val query = mutableMapOf<String, JsonElement>().run {
            restrictSources?.let { put(KeyRestrictSources, JsonLiteral(it)) }
            params.query?.toJsonNoDefaults()?.let { putAll(it.content) }
            JsonObject(this)
        }
        val body = RequestAPIKey(
            ACLs = params.ACLs,
            indices = params.indices,
            description = params.description,
            maxHitsPerQuery = params.maxHitsPerQuery,
            maxQueriesPerIPPerHour = params.maxQueriesPerIPPerHour,
            validity = params.validity,
            query = if (query.isNotEmpty()) query.urlEncode() else null,
            referers = params.referers
        ).stringify()

        return transport.request(HttpMethod.Post, CallType.Write, RouteKeysV1, requestOptions, body)
    }

    override suspend fun updateAPIKey(
        apiKey: APIKey,
        params: APIKeyParams,
        requestOptions: RequestOptions?
    ): RevisionAPIKey {
        val body = RequestAPIKey(
            ACLs = params.ACLs,
            indices = params.indices,
            description = params.description,
            maxHitsPerQuery = params.maxHitsPerQuery,
            maxQueriesPerIPPerHour = params.maxQueriesPerIPPerHour,
            validity = params.validity,
            query = params.query?.toJsonNoDefaults()?.urlEncode(),
            referers = params.referers
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

    override suspend fun restoreAPIKey(apiKey: APIKey, requestOptions: RequestOptions?): CreationAPIKey {
        return CreationAPIKey(
            apiKey,
            transport.request<Creation>(
                HttpMethod.Post,
                CallType.Write,
                "$RouteKeysV1/$apiKey/restore",
                requestOptions,
                ""
            ).createdAt
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