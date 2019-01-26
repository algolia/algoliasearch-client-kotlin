package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.index.ResponseIndex
import com.algolia.search.model.index.Scope


interface EndpointIndex {

    val indexName: IndexName

    suspend fun copyIndex(
        destination: IndexName,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions? = null
    ): ResponseIndex.Update

    suspend fun moveIndex(destination: IndexName, requestOptions: RequestOptions? = null): ResponseIndex.Update

    suspend fun deleteIndex(requestOptions: RequestOptions? = null): ResponseIndex.Delete

    suspend fun clear(requestOptions: RequestOptions? = null): ResponseIndex.Update
}