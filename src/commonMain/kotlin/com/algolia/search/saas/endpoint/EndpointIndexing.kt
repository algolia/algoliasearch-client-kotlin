package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.data.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonObject


interface EndpointIndexing {

    val indexName: IndexName

    suspend fun <T> addObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions? = null
    ): TaskCreateObject

    suspend fun <T> addObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperation

    suspend fun addObject(
        data: JsonObject,
        requestOptions: RequestOptions? = null
    ): TaskCreateObject

    suspend fun addObjects(
        data: List<JsonObject>,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperation

    suspend fun <T : Indexable> replaceObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun <T : Indexable> replaceObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperation

    suspend fun replaceObject(
        data: JsonObject,
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun replaceObjects(
        data: List<Pair<JsonObject, ObjectID>>,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperation

    suspend fun deleteObject(
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): TaskDelete

    suspend fun deleteObjects(
        objectIDs: List<ObjectID>,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperation

    suspend fun deleteObjectBy(
        query: Query,
        requestOptions: RequestOptions? = null
    ): TaskUpdateIndex

    suspend fun <T : Indexable> getObject(
        objectID: ObjectID,
        serializer: KSerializer<T>,
        attributes: List<Attribute> = listOf(),
        requestOptions: RequestOptions? = null
    ): T?

    suspend fun getObject(
        objectID: ObjectID,
        attributes: List<Attribute> = listOf(),
        requestOptions: RequestOptions? = null
    ): JsonObject

    suspend fun <T : Indexable> updateObject(
        data: T,
        serializer: KSerializer<T>,
        createIfNotExists: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun <T : Indexable> updateObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperation

    suspend fun updateObject(
        data: JsonObject,
        objectID: ObjectID,
        createIfNotExists: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun updateObjects(
        data: List<Pair<JsonObject, ObjectID>>,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperation

    suspend fun partialUpdateObject(
        partialUpdate: PartialUpdate,
        objectID: ObjectID,
        createIfNotExists: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun partialUpdateObjects(
        data: List<Pair<PartialUpdate, ObjectID>>,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperation

    suspend fun batch(
        batchOperations: List<BatchOperation>,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperation
}