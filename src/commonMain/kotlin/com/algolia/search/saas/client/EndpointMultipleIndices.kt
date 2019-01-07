package com.algolia.search.saas.client

import com.algolia.search.saas.data.ListIndexes
import com.algolia.search.saas.data.RequestObjects
import kotlinx.serialization.json.JsonObject


interface EndpointMultipleIndices {

    suspend fun listIndexes(requestOptions: RequestOptions? = null): ListIndexes

    suspend fun getObjects(
        request: RequestObjects,
        vararg additionalRequests: RequestObjects,
        requestOptions: RequestOptions? = null
    ): List<JsonObject>
}