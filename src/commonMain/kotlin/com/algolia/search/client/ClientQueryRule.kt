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
import kotlinx.serialization.list


internal class ClientQueryRule(
    val client: Client,
    override val indexName: IndexName
) : EndpointQueryRule,
    Client by client {

    private val route = "/rules"

    override suspend fun saveRule(
        queryRule: QueryRule,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        val bodyString = JsonNoNulls.stringify(QueryRule.serializer(), queryRule)

        return write.retry(
            requestOptions.computedReadTimeout,
            indexName.pathIndexes("$route/${queryRule.objectID}")
        ) { path ->
            httpClient.put<RevisionIndex>(path) {
                body = bodyString
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getRule(objectID: ObjectID, requestOptions: RequestOptions?): QueryRule {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("$route/$objectID")) { path ->
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
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("$route/$objectID")) { path ->
            httpClient.delete<RevisionIndex>(path) {
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun searchRules(query: String?, requestOptions: RequestOptions?): ResponseRules {
        val bodyString = json { query?.let { KeyQuery to it } }.toString()

        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("$route/search")) { path ->
            httpClient.post<ResponseRules>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun clearRules(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): RevisionIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("$route/clear")) { path ->
            httpClient.post<RevisionIndex>(path) {
                body = ""
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun saveRules(
        queryRules: List<QueryRule>,
        forwardToReplicas: Boolean?,
        clearExistingRules: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        val bodyString = JsonNoNulls.stringify(QueryRule.serializer().list, queryRules)

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("$route/batch")) { path ->
            httpClient.post<RevisionIndex>(path) {
                body = bodyString
                setForwardToReplicas(forwardToReplicas)
                parameter(KeyClearExistingRules, clearExistingRules)
                setRequestOptions(requestOptions)
            }
        }
    }
}