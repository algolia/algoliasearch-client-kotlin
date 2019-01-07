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
    ): TaskCreate

    suspend fun addObject(
        json: JsonObject,
        requestOptions: RequestOptions? = null
    ): TaskCreate

    suspend fun <T> updateObject(
        objectID: ObjectID,
        serializer: KSerializer<T>,
        data: T,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun updateObject(
        objectID: ObjectID,
        json: JsonObject,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun deleteObject(
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): TaskDelete

    suspend fun <T : Indexable> getObject(
        objectID: ObjectID,
        serializer: KSerializer<T>,
        vararg attributes: Attribute,
        requestOptions: RequestOptions? = null
    ): T

    suspend fun getObject(
        objectID: ObjectID,
        vararg attributes: Attribute,
        requestOptions: RequestOptions? = null
    ): JsonObject

    suspend fun clearObjects(requestOptions: RequestOptions? = null): TaskUpdateIndex

    suspend fun <T> updateObjectPartially(
        objectID: ObjectID,
        serializer: KSerializer<T>,
        data: T,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun updateObjectPartially(
        objectID: ObjectID,
        json: JsonObject,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun updateObjectPartially(
        objectID: ObjectID,
        updateOperation: UpdateOperation,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

}