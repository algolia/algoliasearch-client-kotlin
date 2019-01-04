package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import kotlinx.serialization.KSerializer


interface EndpointsObjects {

    val indexName: IndexName

    suspend fun <T> addObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions? = null
    ): TaskCreate

    suspend fun <T> updateObject(
        data: T,
        serializer: KSerializer<T>,
        objectId: ObjectId,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun deleteObject(objectId: ObjectId, requestOptions: RequestOptions? = null): TaskDelete
}