package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.QueryRule
import com.algolia.search.saas.data.TaskUpdateIndex
import com.algolia.search.saas.endpoint.EndpointQueryRule
import com.algolia.search.saas.serialize.encodeNoNulls
import com.algolia.search.saas.serialize.toJsonObject
import io.ktor.client.request.put


internal class ClientQueryRule(
    val client: Client,
    override val indexName: IndexName
) : EndpointQueryRule,
    Client by client {

    override suspend fun saveRule(
        queryRule: QueryRule,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): TaskUpdateIndex {
        return write.retry(
            requestOptions.computedReadTimeout,
            indexName.pathIndexes("/rules/${queryRule.objectID}")
        ) { path ->
            httpClient.put<TaskUpdateIndex>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
                body = queryRule.toJsonObject(QueryRule.serializer()).encodeNoNulls().toString()
            }
        }
    }
}