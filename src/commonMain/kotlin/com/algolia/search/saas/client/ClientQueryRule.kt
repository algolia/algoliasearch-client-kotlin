package com.algolia.search.saas.client

import com.algolia.search.saas.model.*
import com.algolia.search.saas.model.query_rule.QueryRule
import com.algolia.search.saas.endpoint.EndpointQueryRule
import com.algolia.search.saas.model.common.TaskUpdate
import com.algolia.search.saas.model.query_rule.QueryRuleHits
import com.algolia.search.saas.serialize.KeyClearExistingRules
import com.algolia.search.saas.serialize.KeyQuery
import com.algolia.search.saas.serialize.encodeNoNulls
import com.algolia.search.saas.serialize.toJsonObject
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
    ): TaskUpdate {
        return write.retry(
            requestOptions.computedReadTimeout,
            indexName.pathIndexes("/rules/${queryRule.objectID}")
        ) { path ->
            httpClient.put<TaskUpdate>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
                body = queryRule.toJsonObject(QueryRule.serializer()).encodeNoNulls().toString()
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
    ): TaskUpdate {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/rules/$objectID")) { path ->
            httpClient.delete<TaskUpdate>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
            }
        }
    }

    override suspend fun searchRules(query: String?, requestOptions: RequestOptions?): QueryRuleHits {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/rules/search")) { path ->
            httpClient.post<QueryRuleHits>(path) {
                setRequestOptions(requestOptions)
                body = json { query?.let { KeyQuery to it } }.toString()
            }
        }
    }

    override suspend fun clearRules(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): TaskUpdate {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/rules/clear")) { path ->
            httpClient.post<TaskUpdate>(path) {
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
    ): TaskUpdate {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/rules/batch")) { path ->
            httpClient.post<TaskUpdate>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
                clearExistingRules?.let { parameter(KeyClearExistingRules, it) }
                body = jsonArray {
                    queryRules.forEach { +it.toJsonObject(QueryRule.serializer()).encodeNoNulls() }
                }.toString()
            }
        }
    }
}