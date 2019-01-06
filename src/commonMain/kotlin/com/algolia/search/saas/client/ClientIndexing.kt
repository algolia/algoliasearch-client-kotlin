package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.serialize.KeyAttributesToRetrieve
import com.algolia.search.saas.serialize.KeyCreateIfNotExists
import io.ktor.client.request.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import kotlinx.serialization.list


class ClientIndexing(
    val client: Client,
    override val indexName: IndexName
) : EndpointsIndexing {

    private suspend fun addObject(payload: String, requestOptions: RequestOptions?): TaskCreate {
        return client.run {
            write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes()) { path ->
                httpClient.post<TaskCreate>(path) {
                    setRequestOptions(requestOptions)
                    body = payload
                }
            }
        }
    }

    override suspend fun <T> addObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): TaskCreate {
        return addObject(Json.stringify(serializer, data), requestOptions)
    }

    override suspend fun addObject(json: JsonObject, requestOptions: RequestOptions?): TaskCreate {
        return addObject(json.toString(), requestOptions)
    }

    private suspend fun updateObject(
        payload: String,
        objectId: ObjectId,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return client.run {
            write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectId")) { path ->
                httpClient.put<TaskUpdateObject>(path) {
                    setRequestOptions(requestOptions)
                    body = payload
                }
            }
        }
    }

    override suspend fun <T> updateObject(
        objectId: ObjectId,
        serializer: KSerializer<T>,
        data: T,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObject(Json.stringify(serializer, data), objectId, requestOptions)
    }

    override suspend fun updateObject(
        objectId: ObjectId,
        json: JsonObject,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObject(json.toString(), objectId, requestOptions)
    }

    override suspend fun deleteObject(objectId: ObjectId, requestOptions: RequestOptions?): TaskDelete {
        return client.run {
            write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectId")) { path ->
                httpClient.delete<TaskDelete>(path)
            }
        }
    }

    private suspend fun getObjectInternal(
        objectId: ObjectId,
        vararg attributes: Attribute,
        requestOptions: RequestOptions?
    ): String {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/$objectId")) { path ->
                httpClient.get<String>(path) {
                    setRequestOptions(requestOptions)
                    if (attributes.isNotEmpty()) {
                        parameter(KeyAttributesToRetrieve, Json.stringify(Attribute.list, attributes.toList()))
                    }
                }
            }
        }
    }

    override suspend fun getObject(
        objectId: ObjectId,
        vararg attributes: Attribute,
        requestOptions: RequestOptions?
    ): JsonObject {
        return getObjectInternal(objectId, *attributes, requestOptions = requestOptions).let {
            Json.nonstrict.parseJson(it).jsonObject
        }
    }

    override suspend fun <T : Indexable> getObject(
        objectId: ObjectId,
        serializer: KSerializer<T>,
        vararg attributes: Attribute,
        requestOptions: RequestOptions?
    ): T {
        return getObjectInternal(objectId, *attributes, requestOptions = requestOptions).let {
            Json.nonstrict.parse(serializer, it)
        }
    }

    override suspend fun clearObjects(requestOptions: RequestOptions?): TaskUpdateIndex {
        return client.run {
            write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/clear")) { path ->
                httpClient.post<TaskUpdateIndex>(path) {
                    setRequestOptions(requestOptions)
                    body = ""
                }
            }
        }
    }

    private suspend fun updateObjectPartially(
        objectId: ObjectId,
        payload: String,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return client.run {
            write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectId/partial")) { path ->
                httpClient.post<TaskUpdateObject>(path) {
                    setRequestOptions(requestOptions)
                    parameter(KeyCreateIfNotExists, createIfNotExists)
                    body = payload
                }
            }
        }
    }

    override suspend fun <T> updateObjectPartially(
        objectId: ObjectId,
        serializer: KSerializer<T>,
        data: T,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObjectPartially(objectId, Json.stringify(serializer, data), createIfNotExists, requestOptions)
    }

    override suspend fun updateObjectPartially(
        objectId: ObjectId,
        json: JsonObject,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObjectPartially(objectId, json.toString(), createIfNotExists, requestOptions)
    }

    override suspend fun updateObjectPartially(
        objectId: ObjectId,
        vararg updateOperations: UpdateOperation,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        val json = json {
            updateOperations.forEach {
                it.attribute.raw to Json.plain.toJson(it, UpdateOperation)
            }
        }
        return updateObjectPartially(objectId, json.toString(), createIfNotExists, requestOptions)
    }
}