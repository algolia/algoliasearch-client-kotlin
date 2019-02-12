package com.algolia.search.endpoint

import com.algolia.search.client.*
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.queryrule.QueryRule
import com.algolia.search.model.response.ResponseRules
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.serialize.JsonNoNulls
import com.algolia.search.serialize.KeyClearExistingRules
import com.algolia.search.serialize.KeyQuery
import io.ktor.client.request.*
import kotlinx.serialization.json.json
import kotlinx.serialization.list


internal class EndpointQueryRuleImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointQueryRule,
    APIWrapper by api {

    private val route = "/rules"

    override suspend fun saveRule(
        queryRule: QueryRule,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        val bodyString = JsonNoNulls.stringify(QueryRule.serializer(), queryRule)

        return write.retry(
            requestOptions.computedReadTimeout,
            indexName.toPath("$route/${queryRule.objectID}")
        ) { url ->
            httpClient.put<RevisionIndex>(url) {
                setConfiguration(api)
                body = bodyString
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getRule(objectID: ObjectID, requestOptions: RequestOptions?): QueryRule {
        return read.retry(requestOptions.computedReadTimeout, indexName.toPath("$route/$objectID")) { url ->
            httpClient.get<QueryRule>(url) {
                setConfiguration(api)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteRule(
        objectID: ObjectID,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.toPath("$route/$objectID")) { url ->
            httpClient.delete<RevisionIndex>(url) {
                setConfiguration(api)
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun searchRules(query: String?, requestOptions: RequestOptions?): ResponseRules {
        val bodyString = json { query?.let { KeyQuery to it } }.toString()

        return read.retry(requestOptions.computedReadTimeout, indexName.toPath("$route/search")) { url ->
            httpClient.post<ResponseRules>(url) {
                setConfiguration(api)
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun clearRules(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): RevisionIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.toPath("$route/clear")) { url ->
            httpClient.post<RevisionIndex>(url) {
                setConfiguration(api)
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

        return write.retry(requestOptions.computedWriteTimeout, indexName.toPath("$route/batch")) { url ->
            httpClient.post<RevisionIndex>(url) {
                setConfiguration(api)
                body = bodyString
                setForwardToReplicas(forwardToReplicas)
                parameter(KeyClearExistingRules, clearExistingRules)
                setRequestOptions(requestOptions)
            }
        }
    }
}