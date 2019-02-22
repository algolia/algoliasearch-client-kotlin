package com.algolia.search.endpoint

import com.algolia.search.client.*
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.request.RequestSearchRules
import com.algolia.search.model.response.ResponseRule
import com.algolia.search.model.response.ResponseRules
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.Rule
import com.algolia.search.serialize.KeyClearExistingRules
import com.algolia.search.serialize.noDefaults
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.list


internal class EndpointRuleImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointRule,
    APIWrapper by api {

    private val route = "/rules"

    override suspend fun saveRule(
        rule: Rule,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        val bodyString = Json.noDefaults.stringify(Rule.serializer(), rule)

        return retryWrite(
            requestOptions,
            indexName.toPath("$route/${rule.objectID}")
        ) { url ->
            httpClient.put<RevisionIndex>(url) {
                body = bodyString
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getRule(objectID: ObjectID, requestOptions: RequestOptions?): ResponseRule {
        return retryRead(requestOptions, indexName.toPath("$route/$objectID")) { url ->
            httpClient.get<ResponseRule>(url) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteRule(
        objectID: ObjectID,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        return retryWrite(requestOptions, indexName.toPath("$route/$objectID")) { url ->
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
        val bodyString = Json.noDefaults.stringify(RequestSearchRules.serializer(), request)

        return retryRead(requestOptions, indexName.toPath("$route/search")) { url ->
            httpClient.post<ResponseRules>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun clearRules(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): RevisionIndex {
        return retryWrite(requestOptions, indexName.toPath("$route/clear")) { url ->
            httpClient.post<RevisionIndex>(url) {
                body = ""
                setForwardToReplicas(forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun saveRules(
        rules: List<Rule>,
        forwardToReplicas: Boolean?,
        clearExistingRules: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        val bodyString = Json.noDefaults.stringify(Rule.serializer().list, rules)

        return retryWrite(requestOptions, indexName.toPath("$route/batch")) { url ->
            httpClient.post<RevisionIndex>(url) {
                body = bodyString
                setForwardToReplicas(forwardToReplicas)
                parameter(KeyClearExistingRules, clearExistingRules)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun replaceAllRules(
        rules: List<Rule>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        return saveRules(rules, forwardToReplicas, true, requestOptions)
    }
}