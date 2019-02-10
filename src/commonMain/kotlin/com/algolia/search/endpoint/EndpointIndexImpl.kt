package com.algolia.search.endpoint

import com.algolia.search.client.APIWrapper
import com.algolia.search.client.RequestOptions
import com.algolia.search.client.setRequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.index.Scope
import com.algolia.search.model.request.RequestCopyOrMove
import com.algolia.search.model.response.deletion.DeletionIndex
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.serialize.JsonNoNulls
import com.algolia.search.serialize.KeyCopy
import com.algolia.search.serialize.KeyMove
import io.ktor.client.request.delete
import io.ktor.client.request.post


internal class EndpointIndexImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointIndex,
    APIWrapper by api {

    private suspend fun copyOrMove(
        destination: IndexName,
        key: String,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        val request = RequestCopyOrMove(key, destination, scopes)
        val bodyString = JsonNoNulls.stringify(RequestCopyOrMove.serializer(), request)

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/operation")) { path ->
            httpClient.post<RevisionIndex>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun copyIndex(
        destination: IndexName,
        scopes: List<Scope>?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        return copyOrMove(destination, KeyCopy, scopes, requestOptions)
    }

    override suspend fun moveIndex(destination: IndexName, requestOptions: RequestOptions?): RevisionIndex {
        return copyOrMove(destination, KeyMove, null, requestOptions)
    }

    override suspend fun deleteIndex(requestOptions: RequestOptions?): DeletionIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes()) { path ->
            httpClient.delete<DeletionIndex>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun copyRule(destination: IndexName, requestOptions: RequestOptions?): RevisionIndex {
        return copyIndex(destination, listOf(Scope.Rules), requestOptions)
    }

    override suspend fun copySettings(destination: IndexName, requestOptions: RequestOptions?): RevisionIndex {
        return copyIndex(destination, listOf(Scope.Settings), requestOptions)
    }

    override suspend fun copySynonyms(destination: IndexName, requestOptions: RequestOptions?): RevisionIndex {
        return copyIndex(destination, listOf(Scope.Synonyms), requestOptions)
    }
}