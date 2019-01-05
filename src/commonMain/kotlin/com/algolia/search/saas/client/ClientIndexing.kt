package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.serialize.KeyAttributesToRetrieve
import io.ktor.client.request.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.list


class ClientIndexing(
    val client: Client,
    override val indexName: IndexName
) : EndpointsIndexing {

    private suspend fun addObject(payload: String, requestOptions: RequestOptions?): TaskCreate {
        return client.run {
            write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes()) { path ->
                httpClient.post<TaskCreate>(path) {
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

    override suspend fun addObject(json: JsonElement, requestOptions: RequestOptions?): TaskCreate {
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
                    body = payload
                }
            }
        }
    }

    override suspend fun <T> updateObject(
        data: T,
        serializer: KSerializer<T>,
        objectId: ObjectId,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObject(Json.stringify(serializer, data), objectId, requestOptions)
    }

    override suspend fun updateObject(
        json: JsonElement,
        objectId: ObjectId,
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
        attributes: List<Attribute>?,
        requestOptions: RequestOptions?
    ): String {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/$objectId")) { path ->
                httpClient.get<String>(path) {
                    attributes?.let {
                        parameter(KeyAttributesToRetrieve, Json.stringify(Attribute.list, it))
                    }
                }
            }
        }
    }

    override suspend fun getObject(
        objectId: ObjectId,
        attributes: List<Attribute>?,
        requestOptions: RequestOptions?
    ): JsonObject {
        return getObjectInternal(objectId, attributes, requestOptions).let { Json.nonstrict.parseJson(it).jsonObject }
    }

    override suspend fun <T : Indexable> getObject(
        serializer: KSerializer<T>,
        objectId: ObjectId,
        attributes: List<Attribute>?,
        requestOptions: RequestOptions?
    ): T {
        return getObjectInternal(objectId, attributes, requestOptions).let { Json.nonstrict.parse(serializer, it) }
    }

    override suspend fun clearObjects(requestOptions: RequestOptions?): TaskUpdateIndex {
        return client.run {
            write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/clear")) { path ->
                httpClient.post<TaskUpdateIndex>(path) {
                    body = ""
                }
            }
        }
    }
}