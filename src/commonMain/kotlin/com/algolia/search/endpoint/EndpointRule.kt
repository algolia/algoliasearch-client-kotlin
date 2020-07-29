package com.algolia.search.endpoint

import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.response.ResponseSearchRules
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.rule.RuleQuery
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/query-rules/?language=kotlin]
 */
public interface EndpointRule {

    public val indexName: IndexName

    /**
     * Create or update a single [rule].
     *
     * @param rule The [Rule] to save.
     * @param forwardToReplicas By default, this method applies only to the specified index. By making this true,
     * the method will also send the rules to all replicas.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun saveRule(
        rule: Rule,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    /**
     * Create or update a specified set of [rules], or all [Rule].
     * Each [Rule] will be created or updated, depending on whether a rule with the same [ObjectID] already exists.
     *
     * @param rules The list of [Rule] to save.
     * @param forwardToReplicas By default, this method applies only to the specified index. By making this true,
     * the method will also send the rules to all replicas.
     * @param clearExistingRules Whether the batch will remove all existing rules before adding/updating the rules.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun saveRules(
        rules: List<Rule>,
        forwardToReplicas: Boolean? = null,
        clearExistingRules: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    /**
     * Get a specific [Rule].
     *
     * @param objectID The [ObjectID] of the rule to retrieve.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun getRule(
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): Rule

    /**
     * Delete a specific [Rule] using its [ObjectID].
     *
     * @param objectID The [ObjectID] of the [Rule] to delete.
     * @param forwardToReplicas Whether to forward the operation to the replica indices.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun deleteRule(
        objectID: ObjectID,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    /**
     * Search for [Rule] matching [RuleQuery].
     *
     * @param query The [RuleQuery].
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun searchRules(
        query: RuleQuery = RuleQuery(),
        requestOptions: RequestOptions? = null
    ): ResponseSearchRules

    /**
     * Delete all [Rule] in an index.
     *
     * @param forwardToReplicas Whether to forward the operation to the replica indices.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun clearRules(
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    /**
     * Push a new set of [rules] and erase all previous ones.
     * This method, like [EndpointIndexing.replaceAllObjects], guarantees zero downtime.
     * All existing [Rule] are deleted and replaced with the new ones, in a single, atomic operation.
     *
     * @param rules The list of [Rule] to replace.
     * @param forwardToReplicas Whether to forward the operation to the replica indices.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun replaceAllRules(
        rules: List<Rule>,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex
}
