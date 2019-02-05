package com.algolia.search.client

import com.algolia.search.endpoint.EndpointSynonym
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.request.RequestSearchSynonyms
import com.algolia.search.response.ResponseSearchSynonyms
import com.algolia.search.response.deletion.DeletionIndex
import com.algolia.search.response.revision.RevisionIndex
import com.algolia.search.response.revision.RevisionObject
import com.algolia.search.serialize.JsonNoNulls
import com.algolia.search.serialize.KeyReplaceExistingSynonyms
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.list


internal class ClientSynonym(
    val client: Client,
    override val indexName: IndexName
) : EndpointSynonym,
    Client by client {

    private val route = "synonyms"

    override suspend fun saveSynonym(
        synonym: Synonym,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionObject {
        val bodyString = Json.stringify(Synonym, synonym)

        return write.retry(
            requestOptions.computedWriteTimeout,
            indexName.pathIndexes("$route/${synonym.objectID}")
        ) { path ->
            httpClient.put<RevisionObject>(path) {
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
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("$route/batch")) { path ->
            httpClient.post<RevisionIndex>(path) {
                body = bodyString
                setForwardToReplicas(forwardToReplicas)
                parameter(KeyReplaceExistingSynonyms, replaceExistingSynonyms)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getSynonym(objectID: ObjectID, requestOptions: RequestOptions?): Synonym {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("$route/$objectID")) { path ->
            httpClient.get<Synonym>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteSynonym(
        objectID: ObjectID,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): DeletionIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("$route/$objectID")) { path ->
            httpClient.delete<DeletionIndex>(path) {
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun searchSynonyms(
        query: String?,
        page: Int?,
        hitsPerPage: Int?,
        synonymType: List<SynonymType>?,
        requestOptions: RequestOptions?
    ): ResponseSearchSynonyms {
        val request = RequestSearchSynonyms(query, page, hitsPerPage, synonymType?.joinToString(",") { it.raw })
        val bodyString = JsonNoNulls.stringify(RequestSearchSynonyms.serializer(), request)

        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("$route/search")) { path ->
            httpClient.post<ResponseSearchSynonyms>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun clearSynonyms(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): RevisionIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("$route/clear")) { path ->
            httpClient.post<RevisionIndex>(path) {
                body = ""
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }
}