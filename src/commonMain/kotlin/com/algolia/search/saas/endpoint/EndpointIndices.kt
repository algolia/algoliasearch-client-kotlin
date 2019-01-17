package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.Scope
import com.algolia.search.saas.data.TaskDelete
import com.algolia.search.saas.data.TaskUpdateIndex


interface EndpointIndices {

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