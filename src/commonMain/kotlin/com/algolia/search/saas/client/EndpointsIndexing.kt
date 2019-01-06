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
        objectId: ObjectId,
        serializer: KSerializer<T>,
        data: T,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun updateObject(
        objectId: ObjectId,
        json: JsonObject,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun deleteObject(
        objectId: ObjectId,
        requestOptions: RequestOptions? = null
    ): TaskDelete

    suspend fun <T : Indexable> getObject(
        objectId: ObjectId,
        serializer: KSerializer<T>,
        vararg attributes: Attribute,
        requestOptions: RequestOptions? = null
    ): T

    suspend fun getObject(
        objectId: ObjectId,
        vararg attributes: Attribute,
        requestOptions: RequestOptions? = null
    ): JsonObject

    suspend fun clearObjects(requestOptions: RequestOptions? = null): TaskUpdateIndex

    suspend fun <T> updateObjectPartially(
        objectId: ObjectId,
        serializer: KSerializer<T>,
        data: T,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun updateObjectPartially(
        objectId: ObjectId,
        json: JsonObject,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

    suspend fun updateObjectPartially(
        objectId: ObjectId,
        vararg updateOperations: UpdateOperation,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): TaskUpdateObject

}