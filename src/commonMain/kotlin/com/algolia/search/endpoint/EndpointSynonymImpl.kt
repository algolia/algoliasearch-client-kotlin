package com.algolia.search.endpoint

import com.algolia.search.client.*
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.request.RequestSearchSynonyms
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.response.deletion.DeletionIndex
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.response.revision.RevisionSynonym
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.KeyReplaceExistingSynonyms
import com.algolia.search.serialize.RouteSynonyms
import com.algolia.search.serialize.noDefaults
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.list


internal class EndpointSynonymImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointSynonym,
    APIWrapper by api {

    override suspend fun saveSynonym(
        synonym: Synonym,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionSynonym {
        val bodyString = Json.stringify(Synonym, synonym)

        return retryWrite(
            requestOptions,
            indexName.toPath("$RouteSynonyms/${synonym.objectID}")
        ) { url ->
            httpClient.put<RevisionSynonym>(url) {
                body = bodyString
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun saveSynonyms(
        synonyms: List<Synonym>,
        forwardToReplicas: Boolean?,
        replaceExistingSynonyms: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        val bodyString = Json.stringify(Synonym.list, synonyms)
        return retryWrite(requestOptions, indexName.toPath("$RouteSynonyms/batch")) { url ->
            httpClient.post<RevisionIndex>(url) {
                body = bodyString
                setForwardToReplicas(forwardToReplicas)
                parameter(KeyReplaceExistingSynonyms, replaceExistingSynonyms)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getSynonym(objectID: ObjectID, requestOptions: RequestOptions?): Synonym {
        return retryRead(requestOptions, indexName.toPath("$RouteSynonyms/$objectID")) { url ->
            httpClient.get<Synonym>(url) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteSynonym(
        objectID: ObjectID,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): DeletionIndex {
        return retryWrite(requestOptions, indexName.toPath("$RouteSynonyms/$objectID")) { url ->
            httpClient.delete<DeletionIndex>(url) {
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun searchSynonyms(
        query: String?,
        page: Int?,
        hitsPerPage: Int?,
        synonymTypes: List<SynonymType>?,
        requestOptions: RequestOptions?
    ): ResponseSearchSynonyms {
        val request = RequestSearchSynonyms(query, page, hitsPerPage, synonymTypes?.joinToString(",") { it.raw })
        val bodyString = Json.noDefaults.stringify(RequestSearchSynonyms.serializer(), request)

        return retryRead(requestOptions, indexName.toPath("$RouteSynonyms/search")) { url ->
            httpClient.post<ResponseSearchSynonyms>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun clearSynonyms(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): RevisionIndex {
        return retryWrite(requestOptions, indexName.toPath("$RouteSynonyms/clear")) { url ->
            httpClient.post<RevisionIndex>(url) {
                body = ""
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun replaceAllSynonyms(
        synonyms: List<Synonym>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        return saveSynonyms(synonyms, forwardToReplicas, true, requestOptions)
    }
}