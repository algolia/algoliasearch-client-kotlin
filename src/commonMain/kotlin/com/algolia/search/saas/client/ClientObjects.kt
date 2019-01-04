package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import io.ktor.client.request.delete
import io.ktor.client.request.post
import io.ktor.client.request.put
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JSON


class ClientObjects(
    val client: Client,
    override val indexName: IndexName
) : EndpointsObjects {

    override suspend fun <T> addObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): TaskCreate {
        return client.run {
            write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes()) { path ->
                httpClient.post<TaskCreate>(path) {
                    body = JSON.stringify(serializer, data)
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
        return client.run {
            write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectId")) { path ->
                httpClient.put<TaskUpdateObject> {
                    body = JSON.stringify(serializer, data)
                }
            }
        }
    }

    override suspend fun deleteObject(objectId: ObjectId, requestOptions: RequestOptions?): TaskDelete {
        return client.run {
            write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectId")) { path ->
                httpClient.delete<TaskDelete>(path)
            }
        }
    }
}