package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.Scope
import com.algolia.search.saas.data.Task
import com.algolia.search.saas.data.TaskDelete


interface EndpointsIndices {

    val indexName: IndexName

    suspend fun copyIndex(
        destination: IndexName,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions? = null
    ): Task

    suspend fun moveIndex(destination: IndexName, requestOptions: RequestOptions? = null): Task

    suspend fun deleteIndex(requestOptions: RequestOptions? = null): TaskDelete
}