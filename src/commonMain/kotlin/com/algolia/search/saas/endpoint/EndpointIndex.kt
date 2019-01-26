package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.model.IndexName
import com.algolia.search.saas.model.index.Scope
import com.algolia.search.saas.model.common.TaskDelete
import com.algolia.search.saas.model.common.TaskUpdate


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