package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.TaskCreate
import com.algolia.search.saas.serialize.KSerializerMapAny
import io.ktor.client.request.post
import kotlinx.serialization.json.JSON


class ClientObjects(
    val client: Client,
    override val indexName: IndexName
) : EndpointsObjects {

    override suspend fun addObject(
        data: Map<String, Any?>,
        objectId: String?,
        requestOptions: RequestOptions?
    ): TaskCreate {
        return client.run {
            val suffix = if (objectId != null) "/$objectId" else null

            write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes(suffix)) { path ->
                httpClient.post<TaskCreate>(path) {
                    body = JSON.stringify(KSerializerMapAny, data)
                }
            }
        }
    }
}