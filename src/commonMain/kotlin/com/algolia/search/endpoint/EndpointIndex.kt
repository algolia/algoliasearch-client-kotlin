package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.index.Scope
import com.algolia.search.model.common.TaskDelete
import com.algolia.search.model.common.TaskUpdate


interface EndpointIndex {

    val indexName: IndexName

    suspend fun copyIndex(
        destination: IndexName,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdate

    suspend fun moveIndex(destination: IndexName, requestOptions: RequestOptions? = null): TaskUpdate

    suspend fun deleteIndex(requestOptions: RequestOptions? = null): TaskDelete

    suspend fun clear(requestOptions: RequestOptions? = null): TaskUpdate
}