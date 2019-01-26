package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.model.IndexName
import com.algolia.search.saas.model.index.Scope
import com.algolia.search.saas.model.TaskDelete
import com.algolia.search.saas.model.TaskUpdateIndex


interface EndpointIndex {

    val indexName: IndexName

    suspend fun copyIndex(
        destination: IndexName,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdateIndex

    suspend fun moveIndex(destination: IndexName, requestOptions: RequestOptions? = null): TaskUpdateIndex

    suspend fun deleteIndex(requestOptions: RequestOptions? = null): TaskDelete

    suspend fun clear(requestOptions: RequestOptions? = null): TaskUpdateIndex
}