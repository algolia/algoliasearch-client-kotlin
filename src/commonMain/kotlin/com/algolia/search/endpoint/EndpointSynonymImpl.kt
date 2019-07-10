package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.requestOptionsBuilder
import com.algolia.search.exception.EmptyListException
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.request.EmptyBody
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.response.deletion.DeletionIndex
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.response.revision.RevisionSynonym
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymQuery
import com.algolia.search.serialize.*
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.list


internal class EndpointSynonymImpl(
    private val transport: Transport,
    override val indexName: IndexName
) : EndpointSynonym {

    override suspend fun saveSynonym(
        synonym: Synonym,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionSynonym {
        val path = indexName.toPath("/$RouteSynonyms/${synonym.objectID}")
        val body = Json.stringify(Synonym, synonym)
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyForwardToReplicas, forwardToReplicas)
        }

        return transport.request(HttpMethod.Put, CallType.Write, path, options, body)
    }

    override suspend fun saveSynonyms(
        synonyms: List<Synonym>,
        forwardToReplicas: Boolean?,
        clearExistingSynonyms: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        if (synonyms.isEmpty()) throw EmptyListException("synonyms")
        val path = indexName.toPath("/$RouteSynonyms/batch")
        val body = Json.stringify(Synonym.list, synonyms)
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyForwardToReplicas, forwardToReplicas)
            parameter(KeyReplaceExistingSynonyms, clearExistingSynonyms)
        }

        return transport.request(HttpMethod.Post, CallType.Write, path, options, body)
    }

    override suspend fun getSynonym(objectID: ObjectID, requestOptions: RequestOptions?): Synonym {
        val path = indexName.toPath("/$RouteSynonyms/$objectID")

        return transport.request(HttpMethod.Get, CallType.Read, path, requestOptions)
    }

    override suspend fun deleteSynonym(
        objectID: ObjectID,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): DeletionIndex {
        val path = indexName.toPath("/$RouteSynonyms/$objectID")
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyForwardToReplicas, forwardToReplicas)
        }

        return transport.request(HttpMethod.Delete, CallType.Write, path, options)
    }

    override suspend fun searchSynonyms(
        query: SynonymQuery,
        requestOptions: RequestOptions?
    ): ResponseSearchSynonyms {
        val path = indexName.toPath("/$RouteSynonyms/search")
        val body = JsonNoDefaults.stringify(SynonymQuery.serializer(), query)

        return transport.request(HttpMethod.Post, CallType.Read, path, requestOptions, body)
    }

    override suspend fun clearSynonyms(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): RevisionIndex {
        val path = indexName.toPath("/$RouteSynonyms/clear")
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyForwardToReplicas, forwardToReplicas)
        }

        return transport.request(HttpMethod.Post, CallType.Write, path, options, EmptyBody)
    }

    override suspend fun replaceAllSynonyms(
        synonyms: List<Synonym>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        return saveSynonyms(synonyms, forwardToReplicas, true, requestOptions)
    }
}