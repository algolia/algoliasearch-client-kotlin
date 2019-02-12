package com.algolia.search.endpoint

import com.algolia.search.client.APIWrapper
import com.algolia.search.client.RequestOptions
import com.algolia.search.client.setForwardToReplicas
import com.algolia.search.client.setRequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.request.RequestSearchSynonyms
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.response.deletion.DeletionIndex
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.response.revision.RevisionSynonym
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.JsonNoNulls
import com.algolia.search.serialize.KeyReplaceExistingSynonyms
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.list


internal class EndpointSynonymImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointSynonym,
    APIWrapper by api {

    private val route = "synonyms"

    override suspend fun saveSynonym(
        synonym: Synonym,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionSynonym {
        val bodyString = Json.stringify(Synonym, synonym)

        return write.retry(
            requestOptions.computedWriteTimeout,
            indexName.toPath("/$route/${synonym.objectID}")
        ) { path ->
            httpClient.put<RevisionSynonym>(path) {
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
        return write.retry(requestOptions.computedWriteTimeout, indexName.toPath("/$route/batch")) { path ->
            httpClient.post<RevisionIndex>(path) {
                body = bodyString
                setForwardToReplicas(forwardToReplicas)
                parameter(KeyReplaceExistingSynonyms, replaceExistingSynonyms)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getSynonym(objectID: ObjectID, requestOptions: RequestOptions?): Synonym {
        return read.retry(requestOptions.computedReadTimeout, indexName.toPath("/$route/$objectID")) { path ->
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
        return write.retry(requestOptions.computedWriteTimeout, indexName.toPath("/$route/$objectID")) { path ->
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

        return read.retry(requestOptions.computedReadTimeout, indexName.toPath("/$route/search")) { path ->
            httpClient.post<ResponseSearchSynonyms>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun clearSynonyms(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): RevisionIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.toPath("/$route/clear")) { path ->
            httpClient.post<RevisionIndex>(path) {
                body = ""
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }
}