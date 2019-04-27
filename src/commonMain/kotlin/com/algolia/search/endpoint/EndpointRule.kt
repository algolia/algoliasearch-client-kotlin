package com.algolia.search.endpoint

import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.response.ResponseRule
import com.algolia.search.model.response.ResponseSearchRules
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.rule.RuleQuery
import com.algolia.search.transport.RequestOptions


/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/query-rules/?language=kotlin]
 */
public interface EndpointRule {

    val indexName: IndexName

    suspend fun saveRule(
        rule: Rule,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    suspend fun saveRules(
        rules: List<Rule>,
        forwardToReplicas: Boolean? = null,
        clearExistingRules: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    suspend fun getRule(
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): ResponseRule

    suspend fun deleteRule(
        objectID: ObjectID,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    suspend fun searchRules(
        query: RuleQuery = RuleQuery(),
        requestOptions: RequestOptions? = null
    ): ResponseSearchRules

    suspend fun clearRules(
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    suspend fun replaceAllRules(
        rules: List<Rule>,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex
}