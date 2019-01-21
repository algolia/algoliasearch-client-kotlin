package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.QueryRule
import com.algolia.search.saas.data.TaskUpdateIndex
import com.algolia.search.saas.endpoint.EndpointQueryRule
import io.ktor.client.request.post
import kotlinx.serialization.json.Json


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
            httpClient.post<TaskUpdateIndex>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
                body = Json.stringify(QueryRule.serializer(), queryRule)
            }
        }
    }
}