package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonObject


interface EndpointsIndexing {

    val indexName: IndexName

    suspend fun <T> addObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions? = null
    ): TaskCreateObject

    suspend fun addObject(
        json: JsonObject,
        requestOptions: RequestOptions? = null
    ): TaskCreateObject

    suspend fun <T : Indexable> replaceObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun replaceObject(
        json: JsonObject,
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun deleteObject(
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): TaskDelete

    suspend fun deleteObjectBy(
        query: Query,
        requestOptions: RequestOptions? = null
    ): TaskUpdateIndex

    suspend fun <T : Indexable> getObject(
        objectID: ObjectID,
        serializer: KSerializer<T>,
        vararg attributes: Attribute,
        requestOptions: RequestOptions? = null
    ): T?

    suspend fun getObject(
        objectID: ObjectID,
        vararg attributes: Attribute,
        requestOptions: RequestOptions? = null
    ): JsonObject

    suspend fun clearObjects(requestOptions: RequestOptions? = null): TaskUpdateIndex

    suspend fun <T : Indexable> updateObject(
        data: T,
        serializer: KSerializer<T>,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun updateObject(
        json: JsonObject,
        objectID: ObjectID,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun updateObject(
        objectID: ObjectID,
        partialUpdate: PartialUpdate,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun batch(
        batchOperation: BatchOperation,
        vararg additionalBatchOperations: BatchOperation,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperation
}