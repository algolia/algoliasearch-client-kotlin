package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.TaskCreate


interface EndpointsObjects {

    val indexName: IndexName

    suspend fun addObject(
        data: Map<String, Any?>,
        objectId: String? = null,
        requestOptions: RequestOptions? = null
    ): TaskCreate
}