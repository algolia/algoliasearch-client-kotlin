package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import kotlinx.serialization.KSerializer


interface EndpointsIndexing {

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

    suspend fun deleteObject(
        objectId: ObjectId,
        requestOptions: RequestOptions? = null
    ): TaskDelete

    suspend fun <T : Indexable> getObject(
        serializer: KSerializer<T>,
        objectId: ObjectId,
        attributes: List<Attribute>? = null,
        requestOptions: RequestOptions? = null
    ): T

    suspend fun clearObjects(requestOptions: RequestOptions? = null): TaskUpdateIndex
}