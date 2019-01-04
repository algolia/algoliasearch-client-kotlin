package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.serialize.KeyAttributesToRetrieve
import io.ktor.client.request.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.list


class ClientIndexing(
    val client: Client,
    override val indexName: IndexName
) : EndpointsIndexing {

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
                httpClient.put<TaskUpdateObject>(path) {
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

    override suspend fun <T : Indexable> getObject(
        serializer: KSerializer<T>,
        objectId: ObjectId,
        attributes: List<Attribute>?,
        requestOptions: RequestOptions?
    ): T {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/$objectId")) { path ->
                httpClient.get<String>(path) {
                    attributes?.let {
                        parameter(KeyAttributesToRetrieve, JSON.stringify(Attribute.list, it))
                    }
                }.let { JSON.nonstrict.parse(serializer, it) }
            }
        }
    }
}