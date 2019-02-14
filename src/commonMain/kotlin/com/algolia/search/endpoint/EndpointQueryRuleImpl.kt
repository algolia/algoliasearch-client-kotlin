package com.algolia.search.endpoint

import com.algolia.search.client.APIWrapper
import com.algolia.search.client.RequestOptions
import com.algolia.search.client.setForwardToReplicas
import com.algolia.search.client.setRequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.queryrule.Anchoring
import com.algolia.search.model.queryrule.QueryRule
import com.algolia.search.model.request.RequestSearchRules
import com.algolia.search.model.response.ResponseQueryRule
import com.algolia.search.model.response.ResponseRules
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.serialize.JsonNoNulls
import com.algolia.search.serialize.KeyClearExistingRules
import io.ktor.client.request.*
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
                body = bodyString
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getRule(objectID: ObjectID, requestOptions: RequestOptions?): ResponseQueryRule {
        return read.retry(requestOptions.computedReadTimeout, indexName.toPath("$route/$objectID")) { url ->
            httpClient.get<ResponseQueryRule>(url) {
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
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun searchRules(
        query: String?,
        anchoring: Anchoring?,
        context: String?,
        page: Int?,
        hitsPerPage: Int?,
        enabled: Boolean?,
        requestOptions: RequestOptions?
    ): ResponseRules {
        val request = RequestSearchRules(query, anchoring, context, page, hitsPerPage, enabled)
        val bodyString = JsonNoNulls.stringify(RequestSearchRules.serializer(), request)

        return read.retry(requestOptions.computedReadTimeout, indexName.toPath("$route/search")) { url ->
            httpClient.post<ResponseRules>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun clearRules(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): RevisionIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.toPath("$route/clear")) { url ->
            httpClient.post<RevisionIndex>(url) {
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
                body = bodyString
                setForwardToReplicas(forwardToReplicas)
                parameter(KeyClearExistingRules, clearExistingRules)
                setRequestOptions(requestOptions)
            }
        }
    }
}