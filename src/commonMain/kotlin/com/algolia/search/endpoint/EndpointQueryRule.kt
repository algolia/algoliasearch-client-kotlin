package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.queryrule.QueryRuleHits
import com.algolia.search.model.common.TaskUpdate
import com.algolia.search.model.queryrule.QueryRule


interface EndpointQueryRule {

    val indexName: IndexName

    suspend fun saveRule(
        queryRule: QueryRule,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdate

    suspend fun saveRules(
        queryRules: List<QueryRule>,
        forwardToReplicas: Boolean? = null,
        clearExistingRules: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdate

    suspend fun getRule(
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): QueryRule

    suspend fun deleteRule(
        objectID: ObjectID,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdate

    suspend fun searchRules(
        query: String? = null,
        requestOptions: RequestOptions? = null
    ): QueryRuleHits

    suspend fun clearRules(
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdate
}