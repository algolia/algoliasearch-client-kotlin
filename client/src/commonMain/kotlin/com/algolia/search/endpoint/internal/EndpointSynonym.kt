@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.internal.requestOptionsBuilder
import com.algolia.search.endpoint.EndpointSynonym
import com.algolia.search.exception.EmptyListException
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.internal.request.EmptyBody
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.response.deletion.DeletionIndex
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.response.revision.RevisionSynonym
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymQuery
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.Route
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.builtins.ListSerializer

internal class EndpointSynonymImpl(
    private val transport: Transport,
    override val indexName: IndexName,
) : EndpointSynonym {

    override suspend fun saveSynonym(
        synonym: Synonym,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?,
    ): RevisionSynonym {
        val path = indexName.toPath("/${Route.Synonyms}/${synonym.objectID}")
        val body = Json.encodeToString(Synonym, synonym)
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.ForwardToReplicas, forwardToReplicas)
        }

        return transport.request(HttpMethod.Put, CallType.Write, path, options, body)
    }

    override suspend fun saveSynonyms(
        synonyms: List<Synonym>,
        forwardToReplicas: Boolean?,
        clearExistingSynonyms: Boolean?,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        if (synonyms.isEmpty()) throw EmptyListException("synonyms")
        val path = indexName.toPath("/${Route.Synonyms}/batch")
        val body = Json.encodeToString(ListSerializer(Synonym), synonyms)
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.ForwardToReplicas, forwardToReplicas)
            parameter(Key.ReplaceExistingSynonyms, clearExistingSynonyms)
        }

        return transport.request(HttpMethod.Post, CallType.Write, path, options, body)
    }

    override suspend fun getSynonym(objectID: ObjectID, requestOptions: RequestOptions?): Synonym {
        val path = indexName.toPath("/${Route.Synonyms}/$objectID")

        return transport.request(HttpMethod.Get, CallType.Read, path, requestOptions)
    }

    override suspend fun deleteSynonym(
        objectID: ObjectID,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?,
    ): DeletionIndex {
        val path = indexName.toPath("/${Route.Synonyms}/$objectID")
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.ForwardToReplicas, forwardToReplicas)
        }

        return transport.request(HttpMethod.Delete, CallType.Write, path, options)
    }

    override suspend fun searchSynonyms(
        query: SynonymQuery,
        requestOptions: RequestOptions?,
    ): ResponseSearchSynonyms {
        val path = indexName.toPath("/${Route.Synonyms}/search")
        val body = JsonNoDefaults.encodeToString(SynonymQuery.serializer(), query)

        return transport.request(HttpMethod.Post, CallType.Read, path, requestOptions, body)
    }

    override suspend fun clearSynonyms(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): RevisionIndex {
        val path = indexName.toPath("/${Route.Synonyms}/clear")
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.ForwardToReplicas, forwardToReplicas)
        }

        return transport.request(HttpMethod.Post, CallType.Write, path, options, EmptyBody)
    }

    override suspend fun replaceAllSynonyms(
        synonyms: List<Synonym>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        return saveSynonyms(synonyms, forwardToReplicas, true, requestOptions)
    }
}

/**
 * Create an [EndpointSynonym] instance.
 */
internal fun EndpointSynonym(
    transport: Transport,
    indexName: IndexName,
): EndpointSynonym = EndpointSynonymImpl(transport, indexName)
