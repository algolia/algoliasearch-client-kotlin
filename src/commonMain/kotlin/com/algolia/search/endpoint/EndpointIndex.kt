package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.index.Scope
import com.algolia.search.model.response.deletion.DeletionIndex
import com.algolia.search.model.response.revision.RevisionIndex


interface EndpointIndex {

    val indexName: IndexName

    suspend fun copyIndex(
        destination: IndexName,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    suspend fun moveIndex(destination: IndexName, requestOptions: RequestOptions? = null): RevisionIndex

    suspend fun deleteIndex(requestOptions: RequestOptions? = null): DeletionIndex

    suspend fun copyRule(destination: IndexName, requestOptions: RequestOptions? = null): RevisionIndex

    suspend fun copySettings(destination: IndexName, requestOptions: RequestOptions? = null): RevisionIndex

    suspend fun copySynonyms(destination: IndexName, requestOptions: RequestOptions? = null): RevisionIndex
}