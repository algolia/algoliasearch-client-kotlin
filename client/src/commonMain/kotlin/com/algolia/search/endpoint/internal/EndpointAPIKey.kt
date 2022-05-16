@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointAPIKey
import com.algolia.search.model.APIKey
import com.algolia.search.model.apikey.APIKeyParams
import com.algolia.search.model.internal.request.RequestAPIKey
import com.algolia.search.model.response.ResponseAPIKey
import com.algolia.search.model.response.ResponseListAPIKey
import com.algolia.search.model.response.creation.Creation
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.Deletion
import com.algolia.search.model.response.deletion.DeletionAPIKey
import com.algolia.search.model.response.revision.RevisionAPIKey
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.Route
import com.algolia.search.serialize.internal.stringify
import com.algolia.search.serialize.internal.toJsonNoDefaults
import com.algolia.search.serialize.internal.urlEncode
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

internal class EndpointAPIKeyImpl(
    private val transport: Transport,
) : EndpointAPIKey {

    override suspend fun addAPIKey(
        params: APIKeyParams,
        restrictSources: String?,
        requestOptions: RequestOptions?,
    ): CreationAPIKey {
        val query = mutableMapOf<String, JsonElement>().run {
            restrictSources?.let { put(Key.RestrictSources, JsonPrimitive(it)) }
            params.query?.toJsonNoDefaults()?.let { putAll(it) }
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

        return transport.request(HttpMethod.Post, CallType.Write, Route.KeysV1, requestOptions, body)
    }

    override suspend fun updateAPIKey(
        apiKey: APIKey,
        params: APIKeyParams,
        requestOptions: RequestOptions?,
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

        return transport.request(HttpMethod.Put, CallType.Write, "${Route.KeysV1}/$apiKey", requestOptions, body)
    }

    override suspend fun deleteAPIKey(apiKey: APIKey, requestOptions: RequestOptions?): DeletionAPIKey {
        return DeletionAPIKey(
            transport.request<Deletion>(
                HttpMethod.Delete, CallType.Write, "${Route.KeysV1}/$apiKey", requestOptions
            ).deletedAt,
            apiKey
        )
    }

    override suspend fun restoreAPIKey(apiKey: APIKey, requestOptions: RequestOptions?): CreationAPIKey {
        return CreationAPIKey(
            apiKey,
            transport.request<Creation>(
                HttpMethod.Post, CallType.Write, "${Route.KeysV1}/$apiKey/restore", requestOptions, ""
            ).createdAt
        )
    }

    override suspend fun listAPIKeys(requestOptions: RequestOptions?): ResponseListAPIKey {
        return transport.request(HttpMethod.Get, CallType.Read, Route.KeysV1, requestOptions)
    }

    override suspend fun getAPIKey(
        apiKey: APIKey,
        requestOptions: RequestOptions?,
    ): ResponseAPIKey {
        return transport.request(HttpMethod.Get, CallType.Read, "${Route.KeysV1}/$apiKey", requestOptions)
    }
}

/**
 * Create an [EndpointAPIKey] instance.
 */
internal fun EndpointAPIKey(
    transport: Transport,
): EndpointAPIKey = EndpointAPIKeyImpl(transport)
