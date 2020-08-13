@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointIndex
import com.algolia.search.model.IndexName
import com.algolia.search.model.index.Scope
import com.algolia.search.model.request.RequestCopyOrMove
import com.algolia.search.model.response.deletion.DeletionIndex
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.JsonNoDefaults
import com.algolia.search.serialize.KeyCopy
import com.algolia.search.serialize.KeyMove
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.client.features.ResponseException
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode

internal class EndpointIndexImpl(
    private val transport: Transport,
    override val indexName: IndexName,
) : EndpointIndex {

    private suspend fun copyOrMove(
        destination: IndexName,
        key: String,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        val request = RequestCopyOrMove(key, destination, scopes)
        val body = JsonNoDefaults.encodeToString(RequestCopyOrMove.serializer(), request)

        return transport.request(HttpMethod.Post, CallType.Write, indexName.toPath("/operation"), requestOptions, body)
    }

    override suspend fun copyIndex(
        destination: IndexName,
        scopes: List<Scope>?,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        return copyOrMove(destination, KeyCopy, scopes, requestOptions)
    }

    override suspend fun moveIndex(destination: IndexName, requestOptions: RequestOptions?): RevisionIndex {
        return copyOrMove(destination, KeyMove, null, requestOptions)
    }

    override suspend fun deleteIndex(requestOptions: RequestOptions?): DeletionIndex {
        return transport.request(HttpMethod.Delete, CallType.Write, indexName.toPath(), requestOptions)
    }

    override suspend fun copyRules(destination: IndexName, requestOptions: RequestOptions?): RevisionIndex {
        return copyIndex(destination, listOf(Scope.Rules), requestOptions)
    }

    override suspend fun copySettings(destination: IndexName, requestOptions: RequestOptions?): RevisionIndex {
        return copyIndex(destination, listOf(Scope.Settings), requestOptions)
    }

    override suspend fun copySynonyms(destination: IndexName, requestOptions: RequestOptions?): RevisionIndex {
        return copyIndex(destination, listOf(Scope.Synonyms), requestOptions)
    }

    override suspend fun exists(): Boolean {
        try {
            EndpointSearch(transport, indexName).search(Query(responseFields = emptyList()))
        } catch (exception: ResponseException) {
            if (exception.response.status == HttpStatusCode.NotFound) return false
        }
        return true
    }
}

/**
 * Create an [EndpointIndex] instance.
 */
internal fun EndpointIndex(
    transport: Transport,
    indexName: IndexName,
): EndpointIndex = EndpointIndexImpl(transport, indexName)
