package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.serialize.KeyAttributesToRetrieve
import com.algolia.search.saas.serialize.KeyCreateIfNotExists
import io.ktor.client.request.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.list


internal class ClientIndexing(
    val client: AlgoliaClient,
    override val indexName: IndexName
) : EndpointsIndexing,
    Configuration by client,
    Client by client.client {

    private suspend fun addObject(payload: String, requestOptions: RequestOptions?): TaskCreate {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes()) { path ->
            httpClient.post<TaskCreate>(path) {
                setRequestOptions(requestOptions)
                body = payload
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
        objectID: ObjectID,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID")) { path ->
            httpClient.put<TaskUpdateObject>(path) {
                setRequestOptions(requestOptions)
                body = payload
            }
        }
    }

    override suspend fun <T : Indexable> replaceObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObject(Json.stringify(serializer, data), data.objectID, requestOptions)
    }

    override suspend fun replaceObject(
        json: JsonObject,
        objectID: ObjectID,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObject(json.toString(), objectID, requestOptions)
    }

    override suspend fun deleteObject(objectID: ObjectID, requestOptions: RequestOptions?): TaskDelete {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID")) { path ->
            httpClient.delete<TaskDelete>(path)
        }
    }

    private suspend fun getObjectInternal(
        objectID: ObjectID,
        vararg attributes: Attribute,
        requestOptions: RequestOptions?
    ): String {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/$objectID")) { path ->
            httpClient.get<String>(path) {
                setRequestOptions(requestOptions)
                if (attributes.isNotEmpty()) {
                    parameter(KeyAttributesToRetrieve, Json.stringify(Attribute.list, attributes.toList()))
                }
            }
        }
    }

    override suspend fun getObject(
        objectID: ObjectID,
        vararg attributes: Attribute,
        requestOptions: RequestOptions?
    ): JsonObject {
        return getObjectInternal(objectID, *attributes, requestOptions = requestOptions).let {
            Json.nonstrict.parseJson(it).jsonObject
        }
    }

    override suspend fun <T : Indexable> getObject(
        objectID: ObjectID,
        serializer: KSerializer<T>,
        vararg attributes: Attribute,
        requestOptions: RequestOptions?
    ): T {
        return getObjectInternal(objectID, *attributes, requestOptions = requestOptions).let {
            Json.nonstrict.parse(serializer, it)
        }
    }

    override suspend fun clearObjects(requestOptions: RequestOptions?): TaskUpdateIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/clear")) { path ->
            httpClient.post<TaskUpdateIndex>(path) {
                setRequestOptions(requestOptions)
                body = ""
            }
        }
    }

    private suspend fun updateObjectPartially(
        payload: String,
        objectID: ObjectID,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID/partial")) { path ->
            httpClient.post<TaskUpdateObject>(path) {
                setRequestOptions(requestOptions)
                parameter(KeyCreateIfNotExists, createIfNotExists)
                body = payload
            }
        }
    }

    override suspend fun <T : Indexable> updateObjectPartially(
        data: T,
        serializer: KSerializer<T>,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObjectPartially(Json.stringify(serializer, data), data.objectID, createIfNotExists, requestOptions)
    }

    override suspend fun updateObjectPartially(
        json: JsonObject,
        objectID: ObjectID,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObjectPartially(json.toString(), objectID, createIfNotExists, requestOptions)
    }

    override suspend fun updateObjectPartially(
        objectID: ObjectID,
        partialUpdate: PartialUpdate,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        val payload = Json.plain.toJson(partialUpdate, PartialUpdate).toString()

        return updateObjectPartially(payload, objectID, createIfNotExists, requestOptions)
    }
}