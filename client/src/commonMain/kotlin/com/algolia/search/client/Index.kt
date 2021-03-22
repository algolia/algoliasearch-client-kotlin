package com.algolia.search.client

import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.endpoint.EndpointAnswers
import com.algolia.search.endpoint.EndpointIndex
import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.endpoint.EndpointRule
import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.endpoint.EndpointSettings
import com.algolia.search.endpoint.EndpointSynonym
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchRules
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.rule.RuleQuery
import com.algolia.search.model.search.Query
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymQuery
import com.algolia.search.transport.RequestOptions

/**
 * The main entry point for performing operations on a single index.
 */
public interface Index :
    EndpointSearch,
    EndpointSettings,
    EndpointAdvanced,
    EndpointIndex,
    EndpointIndexing,
    EndpointSynonym,
    EndpointRule,
    EndpointAnswers {

    /**
     * Index name.
     */
    override val indexName: IndexName

    /**
     * Iterate over all [Rule] in the index.
     *
     * @see [searchRules]
     *
     * @param query The [RuleQuery] used to search.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun browseRules(
        query: RuleQuery = RuleQuery(),
        requestOptions: RequestOptions? = null,
    ): List<ResponseSearchRules>

    /**
     * Iterate over all [Synonym] in the index.
     *
     * @see [searchSynonyms]
     *
     * @param query The [SynonymQuery] used to search.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun browseSynonyms(
        query: SynonymQuery = SynonymQuery(),
        requestOptions: RequestOptions? = null,
    ): List<ResponseSearchSynonyms>

    /**
     * Iterate over all objects in the index.
     *
     * @see [browse]
     *
     * @param query The [Query] used to search.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun browseObjects(
        query: Query = Query(),
        requestOptions: RequestOptions? = null,
    ): List<ResponseSearch>

    public companion object
}
