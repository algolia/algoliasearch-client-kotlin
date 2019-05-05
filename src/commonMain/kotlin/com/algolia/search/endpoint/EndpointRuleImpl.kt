package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.requestOptionsBuilder
import com.algolia.search.exception.EmptyListException
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.response.ResponseSearchRules
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.rule.RuleQuery
import com.algolia.search.serialize.JsonNoDefaults
import com.algolia.search.serialize.KeyClearExistingRules
import com.algolia.search.serialize.KeyForwardToReplicas
import com.algolia.search.serialize.RouteRules
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.list


internal class EndpointRuleImpl(
    private val transport: Transport,
    override val indexName: IndexName
) : EndpointRule {

    override suspend fun saveRule(
        rule: Rule,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        val path = indexName.toPath("/$RouteRules/${rule.objectID}")
        val body = JsonNoDefaults.stringify(Rule.serializer(), rule)
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyForwardToReplicas, forwardToReplicas)
        }

        return transport.request(HttpMethod.Put, CallType.Write, path, options, body)
    }

    override suspend fun getRule(objectID: ObjectID, requestOptions: RequestOptions?): Rule {
        val path = indexName.toPath("/$RouteRules/$objectID")

        return transport.request(HttpMethod.Get, CallType.Read, path, requestOptions)
    }

    override suspend fun deleteRule(
        objectID: ObjectID,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        val path = indexName.toPath("/$RouteRules/$objectID")
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyForwardToReplicas, forwardToReplicas)
        }
        return transport.request(HttpMethod.Delete, CallType.Write, path, options)
    }

    override suspend fun searchRules(
        query: RuleQuery,
        requestOptions: RequestOptions?
    ): ResponseSearchRules {
        val path = indexName.toPath("/$RouteRules/search")
        val body = JsonNoDefaults.stringify(RuleQuery.serializer(), query)

        return transport.request(HttpMethod.Post, CallType.Read, path, requestOptions, body)
    }

    override suspend fun clearRules(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): RevisionIndex {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyForwardToReplicas, forwardToReplicas)
        }

        return transport.request(HttpMethod.Post, CallType.Write, indexName.toPath("/$RouteRules/clear"), options, "")
    }

    override suspend fun saveRules(
        rules: List<Rule>,
        forwardToReplicas: Boolean?,
        clearExistingRules: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        if (rules.isEmpty()) throw EmptyListException("rules")
        val body = JsonNoDefaults.stringify(Rule.serializer().list, rules)
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyForwardToReplicas, forwardToReplicas)
            parameter(KeyClearExistingRules, clearExistingRules)
        }

        return transport.request(HttpMethod.Post, CallType.Write, indexName.toPath("/$RouteRules/batch"), options, body)
    }

    override suspend fun replaceAllRules(
        rules: List<Rule>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        return saveRules(rules, forwardToReplicas, true, requestOptions)
    }
}