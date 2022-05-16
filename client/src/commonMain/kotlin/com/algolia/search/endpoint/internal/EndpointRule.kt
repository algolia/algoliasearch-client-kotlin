@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.internal.requestOptionsBuilder
import com.algolia.search.endpoint.EndpointRule
import com.algolia.search.exception.EmptyListException
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.internal.request.EmptyBody
import com.algolia.search.model.response.ResponseSearchRules
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.rule.RuleQuery
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.Route
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.builtins.ListSerializer

internal class EndpointRuleImpl(
    private val transport: Transport,
    override val indexName: IndexName,
) : EndpointRule {

    override suspend fun saveRule(
        rule: Rule,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        val path = indexName.toPath("/${Route.Rules}/${rule.objectID}")
        val body = JsonNoDefaults.encodeToString(Rule.serializer(), rule)
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.ForwardToReplicas, forwardToReplicas)
        }

        return transport.request(HttpMethod.Put, CallType.Write, path, options, body)
    }

    override suspend fun getRule(objectID: ObjectID, requestOptions: RequestOptions?): Rule {
        val path = indexName.toPath("/${Route.Rules}/$objectID")

        return transport.request(HttpMethod.Get, CallType.Read, path, requestOptions)
    }

    override suspend fun deleteRule(
        objectID: ObjectID,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        val path = indexName.toPath("/${Route.Rules}/$objectID")
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.ForwardToReplicas, forwardToReplicas)
        }
        return transport.request(HttpMethod.Delete, CallType.Write, path, options)
    }

    override suspend fun searchRules(
        query: RuleQuery,
        requestOptions: RequestOptions?,
    ): ResponseSearchRules {
        val path = indexName.toPath("/${Route.Rules}/search")
        val body = JsonNoDefaults.encodeToString(RuleQuery.serializer(), query)

        return transport.request(HttpMethod.Post, CallType.Read, path, requestOptions, body)
    }

    override suspend fun clearRules(forwardToReplicas: Boolean?, requestOptions: RequestOptions?): RevisionIndex {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.ForwardToReplicas, forwardToReplicas)
        }

        return transport.request(
            HttpMethod.Post,
            CallType.Write,
            indexName.toPath("/${Route.Rules}/clear"),
            options,
            EmptyBody
        )
    }

    override suspend fun saveRules(
        rules: List<Rule>,
        forwardToReplicas: Boolean?,
        clearExistingRules: Boolean?,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        if (rules.isEmpty()) throw EmptyListException("rules")
        val body = JsonNoDefaults.encodeToString(ListSerializer(Rule.serializer()), rules)
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.ForwardToReplicas, forwardToReplicas)
            parameter(Key.ClearExistingRules, clearExistingRules)
        }

        return transport.request(HttpMethod.Post, CallType.Write, indexName.toPath("/${Route.Rules}/batch"), options, body)
    }

    override suspend fun replaceAllRules(
        rules: List<Rule>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        return saveRules(rules, forwardToReplicas, true, requestOptions)
    }
}

/**
 * Create an [EndpointRule] instance.
 */
internal fun EndpointRule(
    transport: Transport,
    indexName: IndexName,
): EndpointRule = EndpointRuleImpl(transport, indexName)
