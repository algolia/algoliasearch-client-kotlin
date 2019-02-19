package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.QueryRule
import com.algolia.search.model.response.ResponseQueryRule
import com.algolia.search.model.response.ResponseRules
import com.algolia.search.model.response.revision.RevisionIndex


interface EndpointQueryRule {

    val indexName: IndexName

    suspend fun saveRule(
        queryRule: QueryRule,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    suspend fun saveRules(
        queryRules: List<QueryRule>,
        forwardToReplicas: Boolean? = null,
        clearExistingRules: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    suspend fun getRule(
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): ResponseQueryRule

    suspend fun deleteRule(
        objectID: ObjectID,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    suspend fun searchRules(
        query: String? = null,
        anchoring: Anchoring? = null,
        context: String? = null,
        page: Int? = null,
        hitsPerPage: Int? = null,
        enabled: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): ResponseRules

    suspend fun clearRules(
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    suspend fun replaceAllRules(
        queryRules: List<QueryRule>,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex
}