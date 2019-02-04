package com.algolia.search.client

import com.algolia.search.endpoint.EndpointQueryRule
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.queryrule.QueryRule
import com.algolia.search.response.ResponseRules
import com.algolia.search.response.revision.RevisionIndex
import com.algolia.search.serialize.JsonNoNulls
import com.algolia.search.serialize.KeyClearExistingRules
import com.algolia.search.serialize.KeyQuery
import io.ktor.client.request.*
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


internal class ClientQueryRule(
    val client: Client,
    override val indexName: IndexName
) : EndpointQueryRule,
    Client by client {

    override suspend fun saveRule(
        queryRule: QueryRule,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        return write.retry(
            requestOptions.computedReadTimeout,
            indexName.pathIndexes("/rules/${queryRule.objectID}")
        ) { path ->
            httpClient.put<RevisionIndex>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
                body = JsonNoNulls.stringify(QueryRule.serializer(), queryRule)
            }
        }
    }

    override suspend fun getRule(objectID: ObjectID, requestOptions: RequestOptions?): QueryRule {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/rules/$objectID")) { path ->
            httpClient.get<QueryRule>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteRule(
        objectID: ObjectID,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/rules/$objectID")) { path ->
            httpClient.delete<RevisionIndex>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
            }
        }
    }

    override suspend fun searchRules(query: String?, requestOptions: RequestOptions?): ResponseRules {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/rules/search")) { path ->
            httpClient.post<ResponseRules>(path) {
                setRequestOptions(requestOptions)
                body = json { query?.let { KeyQuery to it } }.toString()
            }
        }
    }

    override suspend fun clearRules(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): RevisionIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/rules/clear")) { path ->
            httpClient.post<RevisionIndex>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
                body = ""
            }
        }
    }

    override suspend fun saveRules(
        queryRules: List<QueryRule>,
        forwardToReplicas: Boolean?,
        clearExistingRules: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/rules/batch")) { path ->
            httpClient.post<RevisionIndex>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
                clearExistingRules?.let { parameter(KeyClearExistingRules, it) }
                body = jsonArray {
                    queryRules.forEach { +JsonNoNulls.toJson(QueryRule.serializer(), it) }
                }.toString()
            }
        }
    }
}